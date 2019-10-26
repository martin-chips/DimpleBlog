package com.dimple.common.exception.user;

/**
 * @className: CaptchaExpireException
 * @description: 验证码失效异常类
 * @author: Dimple
 * @date: 10/22/19
 */
public class CaptchaExpireException extends UserException {


    public CaptchaExpireException() {
        super("user.captcha.expire", null);
    }
}
