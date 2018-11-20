package com.dimple.bean;

import java.io.Serializable;

public class Permission implements Serializable {
    /**
     * 权限的id
     */
    private Integer permissionId;

    /**
     * 权限的名称，如：user:update
     */
    private String name;

    /**
     * 权限的显示名称，如：用户更新
     */
    private String description;

    /**
     * permission
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * 权限的名称，如：user:update
     * @return name 权限的名称，如：user:update
     */
    public String getName() {
        return name;
    }

    /**
     * 权限的名称，如：user:update
     * @param name 权限的名称，如：user:update
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 权限的显示名称，如：用户更新
     * @return description 权限的显示名称，如：用户更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 权限的显示名称，如：用户更新
     * @param description 权限的显示名称，如：用户更新
     */
    public void setDescription(String description) {
        this.description = description;
    }
}