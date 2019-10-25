package com.dimple.common.exception.user;

/**
 * @className: UserPasswordNotMatchException
 * @description: 用户密码不正确或不符合规范异常类
 * @author: Dimple
 * @date: 10/22/19
 */
public class UserPasswordNotMatchException extends UserException {

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
