package com.dimple.common.exception.user;

/**
 * @className: RoleBlockedException
 * @description: 角色锁定异常类
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class RoleBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super("role.blocked", null);
    }

}
