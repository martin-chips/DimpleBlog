package com.dimple.common.exception.user;

/**
 * @className: CaptchaException
 * @description: 验证码错误异常类
 * @author: Dimple
 * @date: 10/22/19
 */
public class CaptchaException extends UserException {

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
