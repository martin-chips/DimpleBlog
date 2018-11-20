package com.dimple.bean;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户的id
     */
    private Integer userId;

    /**
     * 用户的姓名，如：张三
     */
    private String userName;

    /**
     * 用户的登录名称，如：zhangsan
     */
    private String userLoginId;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 是否锁定
     */
    private Byte locked;

    /**
     * user
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户的id
     * @return user_id 用户的id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户的id
     * @param userId 用户的id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 用户的姓名，如：张三
     * @return user_name 用户的姓名，如：张三
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户的姓名，如：张三
     * @param userName 用户的姓名，如：张三
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户的登录名称，如：zhangsan
     * @return user_login_id 用户的登录名称，如：zhangsan
     */
    public String getUserLoginId() {
        return userLoginId;
    }

    /**
     * 用户的登录名称，如：zhangsan
     * @param userLoginId 用户的登录名称，如：zhangsan
     */
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 盐
     * @return salt 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 是否锁定
     * @return locked 是否锁定
     */
    public Byte getLocked() {
        return locked;
    }

    /**
     * 是否锁定
     * @param locked 是否锁定
     */
    public void setLocked(Byte locked) {
        this.locked = locked;
    }
}