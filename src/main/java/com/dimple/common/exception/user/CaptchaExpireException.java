package com.dimple.common.exception.user;

/**
 * @className: CaptchaExpireException
 * @description: if captcha is expired, will throw this exception
 * @author: Dimple
 * @date: 10/22/19
 */
public class CaptchaExpireException extends UserException {


    public CaptchaExpireException() {
        super("user.captcha.expire", null);
    }
}
