package com.dimple.project.common.service;

import com.dimple.project.common.domain.ReplayEmail;

/**
 * @className: EmailService
 * @description: Email service
 * @author: Dimple
 * @date: 01/15/20
 */
public interface EmailService {

    void sendHtmlMail(String to, String title, String content);

    void sendReplyEmail(String url, String htmlContent, String nickName, String email, ReplayEmail replayEmail);

    void sendLinkApplyResult(boolean success,String reason);
}
