package com.dimple.service.impl;

import com.dimple.framework.constant.Status;
import com.dimple.framework.exception.user.*;
import com.dimple.service.LoginService;
import com.dimple.utils.MessageUtil;
import com.dimple.utils.ServletUtil;
import com.dimple.framework.log.AsyncLog;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AsyncLog asyncLog;

    @Override
    public Result login(String loginId, String password) {

        String kaptcha = ServletUtil.getRequest().getParameter("kaptcha");
        //获取kaptcha生成的验证码
        String kaptchaExpected = (String) ServletUtil.getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //验证码校验
        if (StringUtils.isBlank(ServletUtil.getRequest().getParameter("kaptcha")) || !kaptchaExpected.equals(kaptcha)) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.captcha.error"));
            throw new CaptchaException();
        }
        //账号和密码为空校验
        if (StringUtils.isBlank(loginId) || StringUtils.isBlank(password)) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.not.exists"));
            throw new UserAccountNotExistsException();
        }
        //获取Subject对象
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginId, password);
        try {
            currentUser.login(usernamePasswordToken);
            asyncLog.recordLoginLog(loginId, Status.LOGOUT_SUCCESS, MessageUtil.getMessage("user.login.success"));
            return ResultUtil.success();
        } catch (UnknownAccountException e) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.not.exists"));
            throw new UserAccountNotExistsException();
        } catch (IncorrectCredentialsException e) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        } catch (LockedAccountException e) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.account.locked"));
            throw new UserAccountLockedException();
        } catch (AuthenticationException exception) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.authentication.error"));
            throw new UserException("user.authentication.error", null);
        }
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
