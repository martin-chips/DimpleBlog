package com.dimple.project.system.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: RoleMenu
 * @description: 角色和菜单关联 sys_role_menu
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleMenu implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
