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
     * 请求地址
     */
    private String requestUrl;

    /**
     * 类型（1为目录，2为菜单，3为按钮）
     */
    private Integer type;

    /**
     * 显示的图标
     */
    private String icon;

    /**
     * 显示或者隐藏，1表示启用，0表示停用
     */
    private Boolean status;

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

    /**
     * 请求地址
     * @return request_url 请求地址
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 请求地址
     * @param requestUrl 请求地址
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 类型（1为目录，2为菜单，3为按钮）
     * @return type 类型（1为目录，2为菜单，3为按钮）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型（1为目录，2为菜单，3为按钮）
     * @param type 类型（1为目录，2为菜单，3为按钮）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 显示的图标
     * @return icon 显示的图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 显示的图标
     * @param icon 显示的图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 显示或者隐藏，1表示显示，0表示隐藏
     * @return status 显示或者隐藏，1表示显示，0表示隐藏
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 显示或者隐藏，1表示显示，0表示隐藏
     * @param status 显示或者隐藏，1表示显示，0表示隐藏
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}