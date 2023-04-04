package com.dimple.monitor.service.service;

import com.dimple.monitor.service.service.impl.bo.SysUserOnlineBO;
import com.dimple.system.api.model.LoginUser;

/**
 * 在线用户 服务层
 *
 * @author Dimple
 */
public interface SysUserOnlineService {
    /**
     * 通过登录地址查询信息
     *
     * @param ip 登录地址
     * @param user   用户信息
     * @return 在线用户信息
     */
    SysUserOnlineBO selectOnlineByip(String ip, LoginUser user);

    /**
     * 通过用户名称查询信息
     *
     * @param userName 用户名称
     * @param user     用户信息
     * @return 在线用户信息
     */
    SysUserOnlineBO selectOnlineByUserName(String userName, LoginUser user);

    /**
     * 通过登录地址/用户名称查询信息
     *
     * @param ip   登录地址
     * @param userName 用户名称
     * @param user     用户信息
     * @return 在线用户信息
     */
    SysUserOnlineBO selectOnlineByInfo(String ip, String userName, LoginUser user);

    /**
     * 设置在线用户信息
     *
     * @param user 用户信息
     * @return 在线用户
     */
    SysUserOnlineBO loginUserToUserOnline(LoginUser user);
}
