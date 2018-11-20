package com.dimple.bean;

import java.io.Serializable;

public class RolePermission implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色的id
     */
    private Integer roleId;

    /**
     * 权限的id
     */
    private Integer permissionId;

    /**
     * role_permission
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 角色的id
     * @return role_id 角色的id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色的id
     * @param roleId 角色的id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 权限的id
     * @return permission_id 权限的id
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * 权限的id
     * @param permissionId 权限的id
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}