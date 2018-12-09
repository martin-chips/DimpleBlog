package com.dimple.bean;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     * 角色的id
     */
    private Integer roleId;

    /**
     * 角色的名称，如：admin
     */
    private String roleName;

    /**
     * 角色的描述，如：管理员
     */
    private String description;

    /**
     * 角色状态（1表示停用，0表示未停用）
     */
    private Boolean locked;

    /**
     * 
     */
    private Date createTime;

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
     * @return role_name 角色的名称，如：admin
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色的名称，如：admin
     * @param roleName 角色的名称，如：admin
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    /**
     * 角色状态（1表示停用，0表示未停用）
     * @return locked 角色状态（1表示停用，0表示未停用）
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 角色状态（1表示停用，0表示未停用）
     * @param locked 角色状态（1表示停用，0表示未停用）
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * 
     * @return create_time 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}