package com.dimple.project.monitor.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: UserOnline
 * @description: 当前在线会话
 * @author: Dimple
 * @date: 10/22/19
 */
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class UserOnline implements Serializable {
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
    private String ipaddr;

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
