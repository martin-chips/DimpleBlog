package com.dimple.bean;

import java.io.Serializable;

public class Role implements Serializable {
    /**
     * 角色的id
     */
    private Integer roleId;

    /**
     * 角色的名称，如：admin
     */
    private String name;

    /**
     * 角色的描述，如：管理员
     */
    private String description;

    /**
     * role
     */
    private static final long serialVersionUID = 1L;

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
     * 角色的名称，如：admin
     * @return name 角色的名称，如：admin
     */
    public String getName() {
        return name;
    }

    /**
     * 角色的名称，如：admin
     * @param name 角色的名称，如：admin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 角色的描述，如：管理员
     * @return description 角色的描述，如：管理员
     */
    public String getDescription() {
        return description;
    }

    /**
     * 角色的描述，如：管理员
     * @param description 角色的描述，如：管理员
     */
    public void setDescription(String description) {
        this.description = description;
    }
}