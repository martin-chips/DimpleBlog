package com.dimple.common.enums;

/**
 * @className: UserStatus
 * @description: user type
 * @author: Dimple
 * @date: 10/22/19
 */
public enum UserStatus {
    OK("0", "正常"), DISABLE("1", "停用");

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
