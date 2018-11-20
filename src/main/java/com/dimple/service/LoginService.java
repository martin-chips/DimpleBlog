package com.dimple.service;

import com.dimple.utils.message.Result;

/**
 * @ClassName: LoginService
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:21
 * @Version: 1.0
 */
public interface LoginService {
    Result login(String loginId, String password);

    void logout();
}
