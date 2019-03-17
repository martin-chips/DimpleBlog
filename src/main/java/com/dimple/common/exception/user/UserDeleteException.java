package com.dimple.common.exception.user;

/**
 * @className: UserDeleteException
 * @description: 用户账号已被删除
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class UserDeleteException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete", null);
    }
}
