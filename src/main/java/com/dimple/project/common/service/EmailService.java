package com.dimple.project.common.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @className: EmailService
 * @description: Email service
 * @author: Dimple
 * @date: 01/15/20
 */
public interface EmailService {

    /**
     * send html email
     *
     * @param to      send to email
     * @param title   title
     * @param content content（HTML）
     */
    @Async
    void sendHtmlMail(String to, String title, String content);

    @Async
    void sendReplyEmail(String url, String htmlContent, String nickName, String email);
}
