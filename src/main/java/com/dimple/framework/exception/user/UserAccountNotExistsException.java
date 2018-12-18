package com.dimple.framework.exception.user;

/**
 * @ClassName: UserAccountNotExistsException
 * @Description: 用户不存在异常
 * @Auther: Owenb
 * @Date: 12/06/18 15:02
 * @Version: 1.0
 */
public class UserAccountNotExistsException extends UserException {
    public UserAccountNotExistsException() {
        super("user.not.exists", null);
    }
}
