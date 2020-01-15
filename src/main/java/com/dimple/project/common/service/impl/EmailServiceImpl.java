package com.dimple.project.common.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.dimple.common.constant.ConfigKey;
import com.dimple.project.common.service.EmailService;
import com.dimple.project.system.domain.EmailSetting;
import com.dimple.project.system.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: EmailServiceImpl
 * @description: Email service
 * @author: Dimple
 * @date: 01/15/20
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private ConfigService configService;

    @Override
    public void sendHtmlMail(String to, String title, String content) {
        EmailSetting emailSetting = configService.selectConfigByConfigKey(ConfigKey.CONFIG_KEY_EMAIL_SETTING, EmailSetting.class);
        MailAccount mailAccount = new MailAccount()
                .setHost(emailSetting.getHost())
                .setPort(emailSetting.getPort())
                .setAuth(true)
                .setPass(emailSetting.getPassword())
                .setFrom(emailSetting.getUser() + "<" + emailSetting.getFromEmail() + ">")
                .setSslEnable(true);
        Mail.create(mailAccount)
                .setTos(to)
                .setTitle(title)
                .setContent(content)
                .setHtml(true)
                .setUseGlobalSession(false)
                .send();
    }

    @Override
    public void sendReplyEmail(String url, String htmlContent, String nickName, String email) {
        String title = "您在DimpleBlog的评论收到新回复了!";
        String content = "你的评论被" + nickName + "回复了! 回复内容: " + htmlContent + "," + "<a href='" + url + "'>点此快速回复!</a>";
        sendHtmlMail(email, title, content);
    }

}
