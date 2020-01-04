package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: Role
 * @description: 角色表 sys_role
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseEntity implements Serializable {

    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 角色排序
     */
    private String roleSort;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    private boolean flag = false;

    /**
     * 菜单组
     */
    private Long[] menuIds;


    public Role(Long id) {
        this.id = id;
    }

    public static boolean isAdmin(Long roleId) {
        return roleId != null && 1L == roleId;
    }

    public boolean isAdmin() {
        return isAdmin(this.id);
    }
}
