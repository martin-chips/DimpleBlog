package com.dimple.utils;

import com.dimple.modules.BackStageModule.SystemManager.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author : Owenb
 * @version : 1.0
 * @class : ShiroUtil
 * @description : Shiro 的工具类
 * @date : 11/29/18 10:54
 */
public class ShiroUtil {
    /**
     * 获取Subject对象
     *
     * @return 返回Subject对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取Session
     *
     * @return Session对象
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 登出
     */
    public static void logout() {
        getSubject().logout();
    }

    /**
     * 返回User对象
     *
     * @return 当前系统中的User对象
     */
    public static User getUser() {
        return (User) getSubject().getPrincipal();
    }


    /**
     * 返回SessionId
     *
     * @return sessionId字符串
     */
    public static String getSessionId() {
        return String.valueOf(getSubject().getSession().getId());
    }

    public static String getIp() {
        return getSubject().getSession().getHost();
    }
}
