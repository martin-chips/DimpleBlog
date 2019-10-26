package com.dimple.project.system.service.impl;

import org.springframework.stereotype.Service;
import com.dimple.common.utils.StringUtils;
import com.dimple.framework.security.LoginUser;
import com.dimple.project.monitor.domain.SysUserOnline;
import com.dimple.project.system.service.SysUserOnlineService;

/**
 * @className: SysUserOnlineServiceImpl
 * @description: 在线用户 服务层处理
 * @author: Dimple
 * @date: 10/22/19
 */
@Service
public class SysUserOnlineServiceImpl implements SysUserOnlineService {

    @Override
    public SysUserOnline selectOnlineByIpaddr(String ipaddr, LoginUser user) {
        if (StringUtils.equals(ipaddr, user.getIpaddr())) {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline selectOnlineByUserName(String userName, LoginUser user) {
        if (StringUtils.equals(userName, user.getUsername())) {
            return loginUserToUserOnline(user);
        }
        return null;
    }


    @Override
    public SysUserOnline selectOnlineByInfo(String ipaddr, String userName, LoginUser user) {
        if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername())) {
            return loginUserToUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline loginUserToUserOnline(LoginUser user) {
        if (StringUtils.isNull(user) && StringUtils.isNull(user.getUser())) {
            return null;
        }
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline.setTokenId(user.getToken());
        sysUserOnline.setUserName(user.getUsername());
        sysUserOnline.setIpaddr(user.getIpaddr());
        sysUserOnline.setLoginLocation(user.getLoginLocation());
        sysUserOnline.setBrowser(user.getBrowser());
        sysUserOnline.setOs(user.getOs());
        sysUserOnline.setLoginTime(user.getLoginTime());
        return sysUserOnline;
    }
}
