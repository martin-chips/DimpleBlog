package com.dimple.system.service.service.bo;

import lombok.Data;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author Dimple
 */
@Data
public class SysUserRoleBO {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;
}
