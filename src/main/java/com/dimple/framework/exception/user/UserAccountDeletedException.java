package com.dimple.framework.exception.user;

/**
 * @ClassName: UserAccountDeletedException
 * @Description:
 * @Auther: Owenb
 * @Date: 12/06/18 15:03
 * @Version: 1.0
 */
public class UserAccountDeletedException extends UserException {
    public UserAccountDeletedException() {
        super("user.account.deleted", null);
    }
}
