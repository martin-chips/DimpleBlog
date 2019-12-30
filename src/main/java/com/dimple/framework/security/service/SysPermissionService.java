package com.dimple.framework.security.service;

import com.dimple.project.system.domain.SysUser;
import com.dimple.project.system.service.MenuService;
import com.dimple.project.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @className: SysPermissionService
 * @description: 用户权限处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Component
public class SysPermissionService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("*:*:*");
        } else {
            roles.addAll(menuService.selectMenuPermsByUserId(user.getId()));
        }
        return roles;
    }
}
