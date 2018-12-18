package com.dimple.framework.realm;

import com.dimple.bean.Permission;
import com.dimple.bean.Role;
import com.dimple.bean.User;
import com.dimple.framework.exception.user.UserException;
import com.dimple.service.PermissionService;
import com.dimple.service.RoleService;
import com.dimple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

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
    @Lazy //就是这里，必须延时加载，根本原因是bean实例化的顺序上，shiro的bean必须要先实例化，否则@Cacheable注解无效，理论上可以用@Order控制顺序
            UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

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
        List<Role> roles = roleService.findByUserId(user.getUserId());
        for (Role role : roles) {
            info.addRole(role.getRoleName());
            for (Permission permission : permissionService.findByRoleId(role.getRoleId())) {
                info.addStringPermission(permission.getName());
            }
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
        User userDB = userService.findByUserLoginId(loginId);


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
}
