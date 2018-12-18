package com.dimple.framework.exception.user;

import com.dimple.framework.exception.BaseException;

/**
 * @ClassName: UserException
 * @Description: 用户异常基础异常，设置module
 * @Auther: Owenb
 * @Date: 12/06/18 14:16
 * @Version: 1.0
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
