package com.dimple.utils.async.factory;

import com.dimple.bean.LoginLog;
import com.dimple.bean.OperatorLog;
import com.dimple.constant.Status;
import com.dimple.service.LoginLogService;
import com.dimple.service.OperatorLogService;
import com.dimple.utils.*;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimerTask;

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
     * @param operatorLog
     * @return
     */
    @Async
    public void recordOperateLog(OperatorLog operatorLog) {

        operatorLog.setOperatorLocation(AddressUtil.getRealAddressByIP(operatorLog.getOperatorIp()));
        log.info(operatorLog.toString());
        SpringUtil.getBean(OperatorLogService.class).insertOperatorLog(operatorLog);
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
    @Async
    public void recordLoginLog(String loginId, Byte status, String msg, Object... args) {
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
        log.info(loginLog.toString());
        SpringUtil.getBean(LoginLogService.class).insertLoginLog(loginLog);
    }
}
