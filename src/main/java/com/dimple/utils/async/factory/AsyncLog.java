package com.dimple.utils.async.factory;

import com.dimple.bean.LoginLog;
import com.dimple.bean.OperateLog;
import com.dimple.constant.Status;
import com.dimple.service.LoginLogService;
import com.dimple.service.OperateLogService;
import com.dimple.utils.*;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: AsyncLog
 * @Description: 异步记录日志
 * @Auther: Owenb
 * @Date: 12/03/18 20:21
 * @Version: 1.0
 */
@Slf4j
@Component
public class AsyncLog {
    /**
     * 记录操作日志
     *
     * @param operateLog
     * @return
     */

    public void recordOperateLog(OperateLog operateLog) {

        operateLog.setOperateLocation(AddressUtil.getRealAddressByIP(operateLog.getOperateIp()));
        //设置时间
        operateLog.setOperateTime(new Date());
        log.info(operateLog.toString());
        SpringUtil.getBean(OperateLogService.class).insertOperatorLog(operateLog);
    }

    /**
     * 记录用户登录信息
     *
     * @param loginId 用户名
     * @param status  状态(1代表登陆成功，0代表登录失败，2代表退出成功)
     * @param args    参数，可变参数，放在此处用以以log显示信息
     * @param msg     操作信息
     * @return LoginLog对象
     */
    public void recordLoginLog(String loginId, Byte status, String msg, Object... args) {
        if (Status.LOGIN_SUCCESS == status || Status.LOGOUT_SUCCESS == status) {
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
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginName(loginName);
        loginLog.setStatus(status);
        loginLog.setIpAddress(IpUtil.getIpAddr(ServletUtil.getRequest()));
        loginLog.setLoginLocation(AddressUtil.getRealAddressByIP(IpUtil.getIpAddr(ServletUtil.getRequest())));
        loginLog.setBrowser(browser);
        loginLog.setOs(os);
        loginLog.setMsg(msg);
        //设置时间戳
        loginLog.setLoginTime(new Date());
        log.info(loginLog.toString());
        SpringUtil.getBean(LoginLogService.class).insertLoginLog(loginLog);
    }
}
