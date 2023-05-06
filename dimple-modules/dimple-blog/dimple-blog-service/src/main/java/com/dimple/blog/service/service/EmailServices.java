package com.dimple.blog.service.service;

import com.dimple.blog.api.bo.config.BlogGlobalConfig;
import com.dimple.blog.api.bo.config.EmailConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

/**
 * EmailServices
 *
 * @author Dimple
 * @date 3/26/2023
 */
@Service
@Slf4j
public class EmailServices {
    @Autowired
    private BlogConfigService configService;

    @SneakyThrows
    @Async
    public void sendEmail(String emailTo, String subject, String content) {
        BlogGlobalConfig blogGlobalConfig = configService.getBlogConfig();
        EmailConfig emailConfig = blogGlobalConfig.getEmailConfig();
        if (Objects.isNull(emailConfig)) {
            log.error("No email config, just ignore send email: to {}. subject {}. content {}", emailTo, subject, content);
            return;
        }
        JavaMailSender javaMailSender = getJavaMailSender(emailConfig);
        if (Objects.isNull(javaMailSender)) {
            return;
        }
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
        messageHelper.setFrom("DimpleBlog" + "<" + emailConfig.getUsername() + ">");
        messageHelper.setTo(emailTo);
        messageHelper.setSubject(subject);
        // 第二个参数表示是否html，设为true
        messageHelper.setText(content, true);
        javaMailSender.send(message);
    }

    private JavaMailSender getJavaMailSender(EmailConfig emailConfig) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        if (!Objects.isNull(emailConfig.getPort())) {
            mailSender.setPort(emailConfig.getPort());
        }
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());
        return mailSender;
    }
}
