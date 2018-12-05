package com.dimple.service.impl;

import com.dimple.constant.Status;
import com.dimple.service.LoginService;
import com.dimple.utils.ServletUtil;
import com.dimple.utils.async.factory.AsyncLog;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultEnum;
import com.dimple.utils.message.ResultUtil;
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
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "验证码错误");
            return ResultUtil.error(ResultEnum.KAPTCHA_CHECK_ERROR.getCode(), ResultEnum.KAPTCHA_CHECK_ERROR.getMsg());
        }
        //账号和密码为空校验
        if (StringUtils.isBlank(loginId) || StringUtils.isBlank(password)) {
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "账号不存在");
            return ResultUtil.error(ResultEnum.USER_NAME_OR_PASSWORD_IS_NULL.getCode(), ResultEnum.USER_NAME_OR_PASSWORD_IS_NULL.getMsg());
        }
        //获取Subject对象
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginId, password);
        String msg = "";
        Integer code = null;
        try {
            currentUser.login(usernamePasswordToken);
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "登录成功");
            return ResultUtil.success();
        } catch (UnknownAccountException e) {
            code = ResultEnum.USER_NOT_EXIST.getCode();
            msg = ResultEnum.USER_NOT_EXIST.getMsg();
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "账号不存在");
        } catch (IncorrectCredentialsException e) {
            code = ResultEnum.USER_PASSWORD_INCORRECT.getCode();
            msg = ResultEnum.USER_PASSWORD_INCORRECT.getMsg();
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "密码错误");
        } catch (LockedAccountException e) {
            code = ResultEnum.USER_ACCOUNT_LOCKED.getCode();
            msg = ResultEnum.USER_ACCOUNT_LOCKED.getMsg();
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "账户被锁定");
        } catch (AuthenticationException exception) {
            code = ResultEnum.USER_CHECK_ERROR.getCode();
            msg = ResultEnum.USER_CHECK_ERROR.getMsg();
            asyncLog.recordLoginLog(loginId, Status.LOGIN_FAILURE, "身份认证异常");
        }
        return ResultUtil.error(code, msg);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
