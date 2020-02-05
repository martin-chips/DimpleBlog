package com.dimple.project.common.service.impl;

import com.dimple.common.constant.ConfigKey;
import com.dimple.common.utils.StringUtils;
import com.dimple.project.common.domain.ReplayEmail;
import com.dimple.project.common.service.EmailService;
import com.dimple.project.system.domain.EmailSetting;
import com.dimple.project.system.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    @Autowired
    private TemplateEngine templateEngine;

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
            if (StringUtils.isNotEmpty(emailSetting.getStationmasterEmail())) {
                mimeMessageHelper.setBcc(emailSetting.getStationmasterEmail());
            }
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content, true);
            mailSender.setSession(session);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
           log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendReplyEmail(String url, String htmlContent, String nickName, String email, ReplayEmail replayEmail) {
        Context context = new Context();
        //回复的内容
        context.setVariables(replayEmail.toMap());
        String emailContent = templateEngine.process("/mail/ReplyEmail", context);
        String title = "DimpleBlog 留言回复通知!";
        sendHtmlMail(email, title, emailContent);
    }

    @Override
    public void sendLinkApplyResult(boolean success, String reason) {
        String title = "DimpleBlog 申请友链结果通知";
        String content = "";
    }

}
