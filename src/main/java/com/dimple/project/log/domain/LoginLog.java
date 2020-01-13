package com.dimple.project.log.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: LoginLog
 * @description: 系统访问记录表 sys_loginLog
 * @author: Dimple
 * @date: 10/22/19
 */
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class LoginLog extends BaseEntity implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 登录状态 true成功 false失败
     */
    private Boolean status;

    /**
     * 登录IP地址
     */
    private String ip;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * token 信息
     */
    private String token;
}
