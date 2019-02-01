package com.dimple.framework.config.shiro.realm;

import com.dimple.framework.exception.user.UserException;
import com.dimple.modules.BackStageModule.SystemManager.bean.User;
import com.dimple.modules.BackStageModule.SystemManager.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @ClassName: UserRealm
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:45
 * @Version: 1.0
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {


    @Autowired
    AuthService authService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //如果是超级管理员，就授予所有权限
        if (user.getAdmin()) {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        } else {
            //设置角色
            Set<String> roleNames = authService.getRoleNameByUserId(user.getUserId());
            info.setRoles(roleNames);
            //设置权限
            Set<String> permissions = authService.getPermissionByUserId(user.getUserId());
            info.setStringPermissions(permissions);
        }
        return info;
    }

    /**
     * 身份认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws UserException {
        String loginId = (String) token.getPrincipal();
        //获取User信息
        User userDB = authService.getUserByLoginId(loginId);
        if (userDB == null) {
            throw new UnknownAccountException();
        }
        if (userDB.getLocked() == true) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                userDB,
                userDB.getPassword(),
                ByteSource.Util.bytes(userDB.getSalt()),
                getName()
        );
        return info;
    }

    /**
     * 重写，清除授权缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写，清除认证缓存
     *
     * @param principals
     */
    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 清除所有认证信息
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 清除所有授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 清除所有缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
