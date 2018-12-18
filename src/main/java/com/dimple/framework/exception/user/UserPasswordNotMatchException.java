package com.dimple.framework.exception.user;

/**
 * @ClassName: UserPasswordNotMatchException
 * @Description: 用户密码验证失败
 * @Auther: Owenb
 * @Date: 12/06/18 15:05
 * @Version: 1.0
 */
public class UserPasswordNotMatchException extends UserException {
    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
