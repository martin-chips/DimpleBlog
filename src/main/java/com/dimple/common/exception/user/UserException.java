package com.dimple.common.exception.user;

import com.dimple.common.exception.BaseException;

/**
 * @className: UserException
 * @description: 用户信息异常类
 * @author: Dimple
 * @date: 10/22/19
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
