package com.dimple.modules.system.rest;

import cn.hutool.core.lang.Dict;
import com.dimple.annotation.OLog;
import com.dimple.base.BaseEntity;
import com.dimple.exception.BadRequestException;
import com.dimple.modules.system.domain.Role;
import com.dimple.modules.system.service.RoleService;
import com.dimple.modules.system.service.dto.RoleDTO;
import com.dimple.modules.system.service.dto.RoleQueryCriteria;
import com.dimple.modules.system.service.dto.RoleSmallDTO;
import com.dimple.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @className: RoleController
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "系统：角色管理")
@RequestMapping("/api/roles")
public class RoleController {

    private static final String ENTITY_NAME = "role";
    private final RoleService roleService;

    @ApiOperation("获取单个role")
    @GetMapping(value = "/{id}")
    @PreAuthorize("@ps.check('roles:list')")
    public ResponseEntity<Object> query(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
    }

    @OLog("导出角色数据")
    @ApiOperation("导出角色数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@ps.check('role:list')")
    public void download(HttpServletResponse response, RoleQueryCriteria criteria) throws IOException {
        roleService.download(roleService.queryAll(criteria), response);
    }

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    @PreAuthorize("@ps.check('roles:list','user:add','user:edit')")
    public ResponseEntity<Object> query() {
        return new ResponseEntity<>(roleService.queryAll(), HttpStatus.OK);
    }

    @OLog("查询角色")
    @ApiOperation("查询角色")
    @GetMapping
    @PreAuthorize("@ps.check('roles:list')")
    public ResponseEntity<Object> query(RoleQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(roleService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @ApiOperation("获取用户级别")
    @GetMapping(value = "/level")
    public ResponseEntity<Object> getLevel() {
        return new ResponseEntity<>(Dict.create().set("level", getLevels(null)), HttpStatus.OK);
    }

    @OLog("新增角色")
    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("@ps.check('roles:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Role resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        getLevels(resources.getLevel());
        roleService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @OLog("修改角色")
    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("@ps.check('roles:edit')")
    public ResponseEntity<Object> update(@Validated({BaseEntity.Update.class}) @RequestBody Role resources) {
        getLevels(resources.getLevel());
        roleService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("修改角色菜单")
    @ApiOperation("修改角色菜单")
    @PutMapping(value = "/menu")
    @PreAuthorize("@ps.check('roles:edit')")
    public ResponseEntity<Object> updateMenu(@RequestBody Role resources) {
        RoleDTO role = roleService.findById(resources.getId());
        getLevels(role.getLevel());
        roleService.updateMenu(resources, role);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @OLog("删除角色")
    @ApiOperation("删除角色")
    @DeleteMapping
    @PreAuthorize("@ps.check('roles:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        for (Long id : ids) {
            RoleDTO role = roleService.findById(id);
            getLevels(role.getLevel());
        }
        // 验证是否被用户关联
        roleService.verification(ids);
        roleService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 获取用户的角色级别
     *
     * @return /
     */
    private int getLevels(Integer level) {
        List<Integer> levels = roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDTO::getLevel).collect(Collectors.toList());
        int min = Collections.min(levels);
        if (level != null) {
            if (level < min) {
                throw new BadRequestException("权限不足，你的角色级别：" + min + "，低于操作的角色级别：" + level);
            }
        }
        return min;
    }
}
