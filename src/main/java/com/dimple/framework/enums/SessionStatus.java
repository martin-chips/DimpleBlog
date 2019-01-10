package com.dimple.framework.enums;

/**
 * @ClassName: SessionStatus
 * @Description:
 * @Auther: Owenb
 * @Date: 01/10/19 19:50
 * @Version: 1.0
 */
public enum SessionStatus {
    ONLINE("在线", 1), OUTLINE("离线", 0);
    private String description;
    private Integer code;

    SessionStatus(String description, Integer code) {
        this.description = description;
        this.code = code;
    }}
