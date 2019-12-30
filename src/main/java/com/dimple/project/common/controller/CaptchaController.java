package com.dimple.project.common.controller;

import com.dimple.common.constant.Constants;
import com.dimple.common.utils.IdUtils;
import com.dimple.common.utils.VerifyCodeUtils;
import com.dimple.common.utils.sign.Base64;
import com.dimple.framework.redis.RedisCacheService;
import com.dimple.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @className: CaptchaController
 * @description: 验证码操作处理
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@Slf4j
public class CaptchaController {

    final RedisCacheService redisCacheService;

    public CaptchaController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        // 生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        // 唯一标识
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        redisCacheService.setCacheObject(verifyKey, verifyCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 生成图片
        int w = 111;
        int h = 36;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
        try {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("uuid", uuid);
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return AjaxResult.error(e.getMessage());
        } finally {
            stream.close();
        }
    }
}
