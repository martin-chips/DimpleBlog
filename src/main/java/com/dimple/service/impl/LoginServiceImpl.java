package com.dimple.service.impl;

import com.dimple.service.LoginService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultEnum;
import com.dimple.utils.message.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @ClassName: LoginServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:21
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Result login(String loginId, String password) {
        if (StringUtils.isBlank(loginId) || StringUtils.isBlank(password)) {
            return ResultUtil.error(ResultEnum.USER_NAME_OR_PASSWORD_IS_NULL.getCode(), ResultEnum.USER_NAME_OR_PASSWORD_IS_NULL.getMsg());
        }
        //获取Subject对象
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginId, password);
        String msg = "";
        Integer code = null;
        try {
            currentUser.login(usernamePasswordToken);
            Session session = currentUser.getSession();
            session.setAttribute("loginId", loginId);
            return ResultUtil.success();
        } catch (UnknownAccountException e) {
            code = ResultEnum.USER_NOT_EXIST.getCode();
            msg = ResultEnum.USER_NOT_EXIST.getMsg();
        } catch (IncorrectCredentialsException e) {
            code = ResultEnum.USER_PASSWORD_INCORRECT.getCode();
            msg = ResultEnum.USER_PASSWORD_INCORRECT.getMsg();
        } catch (LockedAccountException e) {
            code = ResultEnum.USER_ACCOUNT_LOCKED.getCode();
            msg = ResultEnum.USER_ACCOUNT_LOCKED.getMsg();
        } catch (AuthenticationException exception) {
            code = ResultEnum.USER_CHECK_ERROR.getCode();
            msg = ResultEnum.USER_CHECK_ERROR.getMsg();
        }
        return ResultUtil.error(code, msg);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
