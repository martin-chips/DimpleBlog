package com.dimple.modules.security.config.bean;

import lombok.Data;

/**
 * @className: LoginCode
 * @description: 登录验证码配置信息
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class LoginCode {
    /**
     * 验证码配置
     */
    private LoginCodeEnum codeType;
    /**
     * 验证码有效期 分钟
     */
    private Long expiration = 2L;
    /**
     * 验证码内容长度
     */
    private int length = 2;
    /**
     * 验证码宽度
     */
    private int width = 111;
    /**
     * 验证码高度
     */
    private int height = 36;
}
