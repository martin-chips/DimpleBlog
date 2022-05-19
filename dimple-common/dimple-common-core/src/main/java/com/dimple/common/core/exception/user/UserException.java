package com.dimple.common.core.exception.user;

import com.dimple.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author Dimple
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
