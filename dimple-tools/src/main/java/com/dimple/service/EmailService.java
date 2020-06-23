package com.dimple.service;

import com.dimple.domain.EmailConfig;
import com.dimple.domain.vo.EmailVO;

/**
 * @className: EmailService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface EmailService {

    /**
     * 更新邮件配置
     *
     * @param emailConfig 邮箱配置
     * @param old         /
     * @return /
     * @throws Exception /
     */
    EmailConfig config(EmailConfig emailConfig, EmailConfig old) throws Exception;

    /**
     * 查询配置
     *
     * @return EmailConfig 邮件配置
     */
    EmailConfig find();

    /**
     * 发送邮件
     *
     * @param emailVo     邮件发送的内容
     * @param emailConfig 邮件配置
     * @throws Exception /
     */
    void send(EmailVO emailVo, EmailConfig emailConfig);
}
