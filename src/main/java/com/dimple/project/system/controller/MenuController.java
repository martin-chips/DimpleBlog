package com.dimple.project.system.controller;

import com.dimple.common.constant.UserConstants;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.project.system.domain.Menu;
import com.dimple.project.system.service.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: MenuController
 * @description: 菜单信息
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:list')")
    @GetMapping("/list")
    public AjaxResult list(Menu menu) {
        List<Menu> menus = menuService.selectMenuList(menu);
        return AjaxResult.success(menuService.buildMenuTree(menus));
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(menuService.selectMenuById(id));
    }

    /**
     * 获取菜单下拉树列表
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping("/treeselect")
    public AjaxResult treeselect(Menu dept) {
        List<Menu> menus = menuService.selectMenuList(dept);
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:query')")
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    @ResponseBody
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        return AjaxResult.success(menuService.selectMenuListByRoleId(roleId));
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:add')")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Menu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy(SecurityUtils.getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:edit')")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Menu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@permissionService.hasPermission('system:menu:remove')")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        if (menuService.hasChildByMenuId(id)) {
            return AjaxResult.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(id)) {
            return AjaxResult.error("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(id));
    }
}
