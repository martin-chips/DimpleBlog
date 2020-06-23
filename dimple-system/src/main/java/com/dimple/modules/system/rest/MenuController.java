package com.dimple.modules.system.rest;

import cn.hutool.core.collection.CollectionUtil;
import com.dimple.annotation.OLog;
import com.dimple.exception.BadRequestException;
import com.dimple.modules.system.domain.Menu;
import com.dimple.modules.system.service.MenuService;
import com.dimple.modules.system.service.dto.MenuDTO;
import com.dimple.modules.system.service.dto.MenuQueryCriteria;
import com.dimple.modules.system.service.mapstruct.MenuMapper;
import com.dimple.utils.PageUtil;
import com.dimple.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * @className: MenuController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：菜单管理")
@RequestMapping("/api/menus")
public class MenuController {

    private static final String ENTITY_NAME = "menu";
    private final MenuService menuService;
    private final MenuMapper menuMapper;

    @OLog("导出菜单数据")
    @ApiOperation("导出菜单数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('menu:list')")
    public void download(HttpServletResponse response, MenuQueryCriteria criteria) throws Exception {
        menuService.download(menuService.queryAll(criteria, false), response);
    }

    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    public ResponseEntity<Object> buildMenus() {
        List<MenuDTO> menuDTOList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        List<MenuDTO> menuDTOS = menuService.buildTree(menuDTOList);
        return new ResponseEntity<>(menuService.buildMenus(menuDTOS), HttpStatus.OK);
    }

    @ApiOperation("返回全部的菜单")
    @GetMapping(value = "/lazy")
    @PreAuthorize("@ps.check('menu:list','roles:list')")
    public ResponseEntity<Object> query(@RequestParam Long pid) {
        return new ResponseEntity<>(menuService.getMenus(pid), HttpStatus.OK);
    }

    @OLog("查询菜单")
    @ApiOperation("查询菜单")
    @GetMapping
    @PreAuthorize("@ps.check('menu:list')")
    public ResponseEntity<Object> query(MenuQueryCriteria criteria) throws Exception {
        List<MenuDTO> menuDTOList = menuService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(menuDTOList, menuDTOList.size()), HttpStatus.OK);
    }

    @OLog("查询菜单")
    @ApiOperation("查询菜单:根据ID获取同级与上级数据")
    @PostMapping("/superior")
    @PreAuthorize("@ps.check('menu:list')")
    public ResponseEntity<Object> getSuperior(@RequestBody List<Long> ids) {
        Set<MenuDTO> menuDTOS = new LinkedHashSet<>();
        if (CollectionUtil.isNotEmpty(ids)) {
            for (Long id : ids) {
                MenuDTO menuDto = menuService.findById(id);
                menuDTOS.addAll(menuService.getSuperior(menuDto, new ArrayList<>()));
            }
            return new ResponseEntity<>(menuService.buildTree(new ArrayList<>(menuDTOS)), HttpStatus.OK);
        }
        return new ResponseEntity<>(menuService.getMenus(null), HttpStatus.OK);
    }

    @OLog("新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("@ps.check('menu:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Menu resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        menuService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @OLog("修改菜单")
    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("@ps.check('menu:edit')")
    public ResponseEntity<Object> update(@Validated(Menu.Update.class) @RequestBody Menu resources) {
        menuService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("删除菜单")
    @ApiOperation("删除菜单")
    @DeleteMapping
    @PreAuthorize("@ps.check('menu:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        Set<Menu> menuSet = new HashSet<>();
        for (Long id : ids) {
            List<MenuDTO> menuList = menuService.getMenus(id);
            menuSet.add(menuService.findOne(id));
            menuSet = menuService.getDeleteMenus(menuMapper.toEntity(menuList), menuSet);
        }
        menuService.delete(menuSet);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
