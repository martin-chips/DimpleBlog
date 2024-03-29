package com.dimple.system.web.controller;

import com.dimple.common.core.constant.UserConstants;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.common.security.utils.SecurityUtils;
import com.dimple.system.service.service.SysMenuService;
import com.dimple.system.service.service.bo.SysMenuBO;
import com.dimple.system.web.controller.vo.RouterVO;
import com.dimple.system.web.controller.vo.SysMenuVO;
import com.dimple.system.web.controller.vo.params.SysMenuVOParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单信息
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    public AjaxResult list(SysMenuVOParams menu) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenuBO> menus = menuService.selectMenuList(BeanMapper.convert(menu, SysMenuBO.class), userId);
        return success(BeanMapper.convertList(menus, SysMenuVO.class));
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @RequiresPermissions("system:menu:query")
    @GetMapping("/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId) {
        SysMenuBO sysMenuBO = menuService.selectMenuById(menuId);
        return success(BeanMapper.convert(sysMenuBO, SysMenuVO.class));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SysMenuVOParams menu) {
        Long userId = SecurityUtils.getUserId();

        List<SysMenuBO> menus = menuService.selectMenuList(BeanMapper.convert(menu, SysMenuBO.class), userId);
        return success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping("/roleMenuTreeselect/{roleId}")
    public AjaxResult roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        Long userId = SecurityUtils.getUserId();
        List<SysMenuBO> menus = menuService.selectMenuList(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @RequiresPermissions("system:menu:add")
    @OperationLog(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenuVOParams menu) {
        SysMenuBO sysMenuBO = BeanMapper.convert(menu, SysMenuBO.class);
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(sysMenuBO))) {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        return toAjax(menuService.insertMenu(sysMenuBO));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("system:menu:edit")
    @OperationLog(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenuVOParams menu) {
        SysMenuBO sysMenuBO = BeanMapper.convert(menu, SysMenuBO.class);
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(sysMenuBO))) {
            return error("修改菜单'" + sysMenuBO.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(sysMenuBO.getIsFrame()) && !StringUtils.ishttp(sysMenuBO.getPath())) {
            return error("修改菜单'" + sysMenuBO.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (sysMenuBO.getMenuId().equals(sysMenuBO.getParentId())) {
            return error("修改菜单'" + sysMenuBO.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        return toAjax(menuService.updateMenu(sysMenuBO));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:menu:remove")
    @OperationLog(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId) {
        if (menuService.hasChildByMenuId(menuId)) {
            return warn("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId)) {
            return warn("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenuBO> menus = menuService.selectMenuTreeByUserId(userId);
        return success(BeanMapper.convertList(menuService.buildMenus(menus), RouterVO.class));
    }
}