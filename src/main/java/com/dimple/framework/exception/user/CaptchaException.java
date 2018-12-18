package com.dimple.framework.exception.user;

import com.dimple.framework.exception.BaseException;

/**
 * @ClassName: CaptchaException
 * @Description: 验证码异常
 * @Auther: Owenb
 * @Date: 12/06/18 14:12
 * @Version: 1.0
 */
public class CaptchaException extends UserException {
    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
