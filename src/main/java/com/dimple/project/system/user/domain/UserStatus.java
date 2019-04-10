package com.dimple.project.system.user.domain;

/**
 * @className: UserStatus
 * @description: 用户状态
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public enum UserStatus {
    /**
     * 正常状态
     */
    OK("0", "正常"),
    /**
     * 停用状态
     */
    DISABLE("1", "停用"),
    /**
     * 已删除状态
     */
    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
