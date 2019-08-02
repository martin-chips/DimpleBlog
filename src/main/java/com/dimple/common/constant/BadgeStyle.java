package com.dimple.common.constant;

/**
 * @className: BadgeStyle
 * @description: 前台展示的样badge样式
 * @auther: Dimple
 * @date: 08/02/19
 * @version: 1.0
 */
public enum BadgeStyle {
    /**
     * 主要
     */
    PRIMARY("badge-primary"),
    /**
     * 次要
     */
    SECONDARY("badge-secondary"),
    /**
     * 成功
     */
    SUCCESS("badge-success"),
    /**
     * 危险
     */
    DANGER("badge-danger"),
    /**
     * 警告
     */
    WARNING("badge-warning"),
    /**
     * 信息
     */
    INFO("badge-info"),
    /**
     * 浅色
     */
    LIGHT("badge-light"),
    /**
     * 深色
     */
    DARK("badge-dark"),
    ;

    private String type;

    BadgeStyle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
