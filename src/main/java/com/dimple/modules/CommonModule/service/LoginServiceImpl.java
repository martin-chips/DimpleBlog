package com.dimple.modules.CommonModule.service;

import com.dimple.framework.constant.Status;
import com.dimple.framework.exception.user.CaptchaException;
import com.dimple.framework.exception.user.UserAccountLockedException;
import com.dimple.framework.exception.user.UserAccountNotExistsException;
import com.dimple.framework.exception.user.UserException;
import com.dimple.framework.exception.user.UserPasswordNotMatchException;
import com.dimple.framework.log.AsyncHelper;
import com.dimple.framework.log.AsyncManager;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.utils.MessageUtil;
import com.dimple.utils.ServletUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: LoginServiceImpl
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 17:21
 * @Version: 1.0
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Override
    public Result login(String loginId, String password, Boolean rememberMe) {
        String kaptcha = ServletUtil.getRequest().getParameter("kaptcha");
        //获取kaptcha生成的验证码
        String kaptchaExpected = (String) ServletUtil.getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //验证码校验
        if (StringUtils.isBlank(ServletUtil.getRequest().getParameter("kaptcha")) || !kaptchaExpected.equals(kaptcha)) {
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.FAILURE, MessageUtil.getMessage("user.captcha.error")));
//            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.captcha.error"));
            throw new CaptchaException();
        }
        //账号和密码为空校验
        if (StringUtils.isBlank(loginId) || StringUtils.isBlank(password)) {
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.FAILURE, MessageUtil.getMessage("user.not.exists")));
//            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.not.exists"));
            throw new UserAccountNotExistsException();
        }
        //获取Subject对象
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginId, password, rememberMe);
        try {
            currentUser.login(usernamePasswordToken);
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.SUCCESS, MessageUtil.getMessage("user.login.success")));
//            asyncLog.recordLoginLog(loginId, Status.LOGOUT_SUCCESS, MessageUtil.getMessage("user.login.success"));
            return ResultUtil.success();
        } catch (UnknownAccountException e) {
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.FAILURE, MessageUtil.getMessage("user.not.exists")));

//            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.not.exists"));
            throw new UserAccountNotExistsException();
        } catch (IncorrectCredentialsException e) {
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.FAILURE, MessageUtil.getMessage("user.password.not.match")));

//            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.password.not.match"));
            throw new UserPasswordNotMatchException();
        } catch (LockedAccountException e) {
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.FAILURE, MessageUtil.getMessage("user.account.locked")));

//            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.account.locked"));
            throw new UserAccountLockedException();
        } catch (AuthenticationException exception) {
            AsyncManager.getAsyncManager().execute(AsyncHelper.recordLoginLog(loginId, Status.FAILURE, MessageUtil.getMessage("user.authentication.error")));

//            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, MessageUtil.getMessage("user.authentication.error"));
            throw new UserException("user.authentication.error", null);
        }
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
