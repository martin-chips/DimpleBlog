package com.dimple.project.common.service.impl;

import com.dimple.project.common.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @className: MailService
 * @description: 邮件发送
 * @auther: Dimple
 * @date: 07/18/19
 * @version: 1.0
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        //设置cc自己
        simpleMailMessage.setCc(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setSentDate(new Date());
        log.info("Send simple email->{}", simpleMailMessage);
        try {
            mailSender.send(simpleMailMessage);
        } catch (MailSendException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //使用mimeMessage，MIME协议
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //设置为true说明是HTML
            helper.setText(content, true);
            log.info("Send mime email->{}", mimeMessage);
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailSendException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendAttachmentMail(String to, String subject, String content, String filePath, boolean isHTML) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            FileSystemResource resource = new FileSystemResource(new File(filePath));
            String fileName = resource.getFilename();
            helper.addAttachment(fileName, resource);
            helper.setText(content);
            log.info("Send mime email->{}", mimeMessage);
            mailSender.send(mimeMessage);
        } catch (MessagingException | MailSendException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendTemplateEmail(String to, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //使用模板引擎
            Context context = new Context();
            //context.setVariable();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
