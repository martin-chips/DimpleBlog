package com.dimple.monitor.service.service.impl.bo;

import lombok.Data;

/**
 * 当前在线会话
 *
 * @author Dimple
 */
@Data
public class SysUserOnlineBO {
    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录IP地址
     */
    private String ip;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登录时间
     */
    private Long loginTime;
}
