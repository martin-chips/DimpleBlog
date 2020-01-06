package com.dimple.common.exception.user;

/**
 * @className: UserPasswordNotMatchException
 * @description: user not exist or password are not match
 * @author: Dimple
 * @date: 10/22/19
 */
public class UserPasswordNotMatchException extends UserException {

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
