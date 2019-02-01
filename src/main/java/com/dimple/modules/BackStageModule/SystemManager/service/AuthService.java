package com.dimple.modules.BackStageModule.SystemManager.service;

import com.dimple.modules.BackStageModule.SystemManager.bean.User;

import java.util.Set;

/**
 * @className: AuthService
 * @description:
 * @auther: Owenb
 * @date: 01/31/19
 * @version: 1.0
 */
public interface AuthService {
    /**
     * 根据用户的登录ID获取User的信息
     *
     * @param loginId
     * @return
     */
    User getUserByLoginId(String loginId);

    /**
     * 根据UserId获取角色名称
     *
     * @param userId
     */
    Set<String> getRoleNameByUserId(Integer userId);

    /**
     * 根据用户Id获取权限字符
     *
     * @param userId
     * @return
     */
    Set<String> getPermissionByUserId(Integer userId);
}
