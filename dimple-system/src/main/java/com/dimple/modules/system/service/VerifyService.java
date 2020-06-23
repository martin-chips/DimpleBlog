package com.dimple.modules.system.service;

import com.dimple.domain.vo.EmailVO;


/**
 * @className: VerifyService
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public interface VerifyService {

    /**
     * 发送验证码
     *
     * @param email /
     * @param key   /
     * @return /
     */
    EmailVO sendEmail(String email, String key);


    /**
     * 验证
     *
     * @param code /
     * @param key  /
     */
    void validated(String key, String code);
}
