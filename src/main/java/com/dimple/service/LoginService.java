package com.dimple.service;

import com.dimple.framework.message.Result;

/**
 * @ClassName: LoginService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:21
 * @Version: 1.0
 */
public interface LoginService {
    /**
     * 登陆校验
     *
     * @param loginId  用户登录的ID
     * @param password 用户的密码
     * @param rememberMe
     * @return
     */
    Result login(String loginId, String password, Boolean rememberMe);

    /**
     * 退出
     */
    void logout();
}
