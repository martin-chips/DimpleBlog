package com.dimple.project.system.domain;

import lombok.Data;

/**
 * @className: EmailSetting
 * @description: email setting
 * @author: Dimple
 * @date: 01/15/20
 */
@Data
public class EmailSetting {
    /**
     * 邮件服务器SMTP地址
     */
    private String host;

    /**
     * 邮件服务器 SMTP 端口
     */
    private Integer port;

    /**
     * 发件者用户名，默认为发件人邮箱前缀
     */
    private String user;

    /**
     * 密码或者授权码
     */
    private String password;

    /**
     * 发件人
     */
    private String fromEmail;
    /**
     * 站长邮箱
     */
    private String StationmasterEmail;
}
