package com.dimple.gateway.service;

import com.dimple.common.core.exception.CaptchaException;
import com.dimple.common.core.web.domain.AjaxResult;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author Dimple
 */
public interface ValidateCodeService {
    /**
     * 生成验证码
     */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}
