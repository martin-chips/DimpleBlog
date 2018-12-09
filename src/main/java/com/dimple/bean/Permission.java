package com.dimple.bean;

import java.io.Serializable;

public class Permission implements Serializable {
    /**
     * 权限的id
     */
    private Integer permissionId;

    /**
     * 权限的名称：如友链增加
     */
    private String title;

    /**
     * 权限的名称，如：system:link:insert
     */
    private String name;

    /**
     * 父级权限ID
     */
    private Integer pId;

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
     * 权限的名称：如友链增加
     * @return title 权限的名称：如友链增加
     */
    public String getTitle() {
        return title;
    }

    /**
     * 权限的名称：如友链增加
     * @param title 权限的名称：如友链增加
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 权限的名称，如：system:link:insert
     * @return name 权限的名称，如：system:link:insert
     */
    public String getName() {
        return name;
    }

    /**
     * 权限的名称，如：system:link:insert
     * @param name 权限的名称，如：system:link:insert
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 父级权限ID
     * @return p_id 父级权限ID
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 父级权限ID
     * @param pId 父级权限ID
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }
}