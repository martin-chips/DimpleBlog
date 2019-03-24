package com.dimple.project.system.role.domain;

import lombok.Data;

/**
 * @className: RoleMenu
 * @description: 角色和菜单关联 sys_role_menu
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Data
public class RoleMenu {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
