package com.dimple.controller;

import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName: KaptchaController
 * @Description: 验证码请求的Controller
 * @Auther: Owenb
 * @Date: 11/30/18 9:43
 * @Version: 1.0
 */
@Controller
@Api("验证码Controller")
public class CaptchaController {

    @Autowired
    @Qualifier("kaptchaCharacterImage")
    DefaultKaptcha kaptchaCharacterImage;

    @Autowired
    @Qualifier("kaptchaMathImage")
    DefaultKaptcha kaptchaMathImage;
    /**
     * 配置文件中配置的验证码的类型
     */
    @Value("${kaptcha.type}")
    private String kaptchaType = null;

    @ApiOperation("生成验证码")
    @GetMapping("/api/kaptcha")
    @Log(title = "验证码", operateType = OperateType.GENERATE_CAPTCHA)
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream servletOutputStream = null;
        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String kaptchaStr = null;
            String code = null;
            BufferedImage bi = null;
            if ("math".equals(kaptchaType)) {
                String capText = kaptchaMathImage.createText();
                kaptchaStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = kaptchaMathImage.createImage(kaptchaStr);
            } else {
                kaptchaStr = code = kaptchaCharacterImage.createText();
                bi = kaptchaCharacterImage.createImage(kaptchaStr);
            }
            //放在session中
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            servletOutputStream = response.getOutputStream();
            ImageIO.write(bi, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
