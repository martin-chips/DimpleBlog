package com.dimple.project.system.domain;

import lombok.Data;

/**
 * @className: SysRoleMenu
 * @description: 角色和菜单关联 sys_role_menu
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
public class SysRoleMenu {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
