package com.dimple.common.exception.user;

import com.dimple.common.exception.base.BaseException;

/**
 * @className: UserException
 * @description: 用户信息异常类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
