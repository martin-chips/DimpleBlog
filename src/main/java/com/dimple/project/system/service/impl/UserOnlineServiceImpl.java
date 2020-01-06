package com.dimple.project.system.service.impl;

import org.springframework.stereotype.Service;
import com.dimple.common.utils.StringUtils;
import com.dimple.framework.security.LoginUser;
import com.dimple.project.monitor.domain.UserOnline;
import com.dimple.project.system.service.UserOnlineService;

/**
 * @className: UserOnlineServiceImpl
 * @description: 在线用户 服务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class UserOnlineServiceImpl implements UserOnlineService {

    @Override
    public UserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user) {
        if (StringUtils.equals(ipaddr, user.getIp())) {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public UserOnline selectOnlineByUserName(String userName, LoginUser user) {
        if (StringUtils.equals(userName, user.getUsername())) {
            return loginUserToUserOnline(user);
        }
        return null;
    }


    @Override
    public UserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user) {
        if (StringUtils.equals(ipaddr, user.getIp()) && StringUtils.equals(userName, user.getUsername())) {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public UserOnline loginUserToUserOnline(LoginUser user) {
        if (StringUtils.isNull(user) && StringUtils.isNull(user.getUser())) {
            return null;
        }
        UserOnline userOnline = new UserOnline();
        userOnline.setTokenId(user.getToken());
        userOnline.setUserName(user.getUsername());
        userOnline.setIpaddr(user.getIp());
        userOnline.setLoginLocation(user.getLocation());
        userOnline.setBrowser(user.getBrowser());
        userOnline.setOs(user.getOs());
        userOnline.setLoginTime(user.getLoginTime());
        return userOnline;
    }
}
