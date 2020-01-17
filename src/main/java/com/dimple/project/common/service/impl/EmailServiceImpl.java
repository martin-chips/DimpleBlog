package com.dimple.project.common.service.impl;

import com.dimple.common.constant.ConfigKey;
import com.dimple.project.common.service.EmailService;
import com.dimple.project.system.domain.EmailSetting;
import com.dimple.project.system.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(emailSetting.getUser());
        mailSender.setHost(emailSetting.getHost());
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setPassword(emailSetting.getPassword());
        mailSender.setPort(emailSetting.getPort());

        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailSetting.getHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
        properties.put("mail.smtp.port", emailSetting.getPort());
        properties.put("mail.smtp.socketFactory.port", emailSetting.getPort());
        properties.put("mail.smtp.ssl.enable", true);
        Session session = Session.getInstance(properties);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(emailSetting.getFromEmail());
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content, true);
            mailSender.setSession(session);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
           log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendReplyEmail(String url, String htmlContent, String nickName, String email) {
        String title = "您在DimpleBlog的评论收到新回复了!";
        String content = "你的评论被" + nickName + "回复了! 回复内容: " + htmlContent + "," + "<a href='" + url + "'>点此快速回复!</a>";
        sendHtmlMail(email, title, content);
    }

}
