package com.dimple.exception.user;

/**
 * @ClassName: UserAccountLockedException
 * @Description:
 * @Auther: Owenb
 * @Date: 12/06/18 15:17
 * @Version: 1.0
 */
public class UserAccountLockedException extends UserException {

    public UserAccountLockedException() {
        super("user.account.locked", null);
    }
}
