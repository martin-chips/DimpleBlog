package com.dimple.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.dimple.domain.EmailConfig;
import com.dimple.domain.vo.EmailVO;
import com.dimple.exception.BadRequestException;
import com.dimple.repository.EmailRepository;
import com.dimple.service.EmailService;
import com.dimple.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @className: EmailServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "email")
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Override
    @CachePut(key = "'id:1'")
    @Transactional(rollbackFor = Exception.class)
    public EmailConfig config(EmailConfig emailConfig, EmailConfig old) throws Exception {
        emailConfig.setId(1L);
        if (!emailConfig.getPass().equals(old.getPass())) {
            // 对称加密
            emailConfig.setPass(EncryptUtils.desEncrypt(emailConfig.getPass()));
        }
        return emailRepository.save(emailConfig);
    }

    @Override
    @Cacheable(key = "'id:1'")
    public EmailConfig find() {
        Optional<EmailConfig> emailConfig = emailRepository.findById(1L);
        return emailConfig.orElseGet(EmailConfig::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void send(EmailVO emailVo, EmailConfig emailConfig) {
        if (emailConfig == null) {
            throw new BadRequestException("请先配置，再操作");
        }
        // 封装
        MailAccount account = new MailAccount();
        account.setUser(emailConfig.getUser());
        account.setHost(emailConfig.getHost());
        account.setPort(Integer.parseInt(emailConfig.getPort()));
        account.setAuth(true);
        try {
            // 对称解密
            account.setPass(EncryptUtils.desDecrypt(emailConfig.getPass()));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
        account.setFrom(emailConfig.getUser() + "<" + emailConfig.getFromUser() + ">");
        // ssl方式发送
        account.setSslEnable(true);
        // 使用STARTTLS安全连接
        account.setStarttlsEnable(true);
        String content = emailVo.getContent();
        // 发送
        try {
            int size = emailVo.getTos().size();
            Mail.create(account)
                    .setTos(emailVo.getTos().toArray(new String[size]))
                    .setTitle(emailVo.getSubject())
                    .setContent(content)
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
