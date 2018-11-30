package com.dimple.utils;

import com.dimple.bean.LoginLog;
import com.dimple.constant.Status;
import com.dimple.service.LoginLogService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @ClassName: LoginLogUtil
 * @Description: 登录的日志的记录的util
 * @Auther: Owenb
 * @Date: 11/29/18 17:31
 * @Version: 1.0
 */
@Slf4j
public class LoginLogUtil {

    /**
     * 记录用户登录信息
     *
     * @param loginId 用户名
     * @param status  状态(1代表登陆成功，0代表登录失败，2代表退出成功)
     * @param args    参数，可变参数，放在此处用以以log显示信息
     * @param msg     操作信息
     * @return LoginLog对象
     */
    public static void log(String loginId, Byte status, String msg, Object... args) {
        //拼装Log
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(LogUtil.getBlock(ShiroUtil.getIp()));
        stringBuffer.append(LogUtil.getBlock(AddressUtil.getRealAddressByIP(ShiroUtil.getIp())));
        stringBuffer.append(LogUtil.getBlock(loginId));
        stringBuffer.append(LogUtil.getBlock(status));
        stringBuffer.append(LogUtil.getBlock(msg));
        //显示Log
        log.info(stringBuffer.toString(), args);
        if (Status.LOGIN_SUCCESS == status || Status.LOGOUT == status) {
            saveLoginLog(loginId, msg, Status.SUCCESS);
        } else {
            saveLoginLog(loginId, msg, Status.FAILURE);
        }
    }

    private static void saveLoginLog(String loginName, String msg, Boolean status) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        //获取操作系统
        String os = userAgent.getOperatingSystem().getName();
        //获取浏览器类型
        String browser = userAgent.getBrowser().getName();
        LoginLogService loginLogService = SpringUtil.getBean(LoginLogService.class);
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginName(loginName);
        loginLog.setStatus(status);
        loginLog.setIpAddress(ShiroUtil.getIp());
        loginLog.setLoginLocation(AddressUtil.getRealAddressByIP(ShiroUtil.getIp()));
        loginLog.setBrowser(browser);
        loginLog.setOs(os);
        loginLog.setMsg(msg);
        //设置时间戳
        loginLog.setLoginTime(new Date());
        loginLogService.insertLoginLog(loginLog);
    }


}
