package com.dimple.exception.user;

import com.dimple.exception.BaseException;

/**
 * @ClassName: UserException
 * @Description:
 * @Auther: Owenb
 * @Date: 12/06/18 14:16
 * @Version: 1.0
 */
public class UserException extends BaseException {

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
