package com.dimple.project.common;

import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.framework.security.LoginUser;
import com.dimple.framework.security.service.TokenService;
import com.dimple.project.system.domain.SysUser;

/**
 * @className: DimpleBlogContext
 * @description:
 * @author: Dimple
 * @date: 10/23/19
 */
public class DimpleBlogContext {
    /**
     * 获取当前登录的用户的名字
     *
     * @return 用户名
     */
    public static String getLoginUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取登录的user
     *
     * @return login user
     */
    public static LoginUser getLoginUser() {
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        return loginUser;
    }

    /**
     * 获取系统用户
     *
     * @return 系统用户
     */
    public static SysUser getSysUser() {
        return getLoginUser().getUser();
    }
}
