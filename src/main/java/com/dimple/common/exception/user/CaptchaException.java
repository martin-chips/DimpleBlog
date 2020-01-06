package com.dimple.common.exception.user;

/**
 * @className: CaptchaException
 * @description: if captcha error will throw this exception
 * @author: Dimple
 * @date: 10/22/19
 */
public class CaptchaException extends UserException {

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
