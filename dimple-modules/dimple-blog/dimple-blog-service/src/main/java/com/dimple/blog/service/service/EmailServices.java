package com.dimple.blog.service.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * EmailServices
 *
 * @author BianXiaofeng
 * @date 3/26/2023
 */
@Service
public class EmailServices {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    @SneakyThrows
    public void sendEmail(String emailTo, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo(emailTo);
        messageHelper.setSubject(subject);
        // 第二个参数表示是否html，设为true
        messageHelper.setText(content, true);

        this.javaMailSender.send(message);
    }
}
