package com.dimple.auth.form;

import lombok.Data;

/**
 * 用户登录对象
 *
 * @author Dimple
 */
@Data
public class LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
