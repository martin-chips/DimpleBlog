package com.dimple.framework.manager.factory;

import com.dimple.common.constant.Constants;
import com.dimple.common.utils.LogUtils;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.ip.AddressUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.log.domain.LoginLog;
import com.dimple.project.log.domain.OperateLog;
import com.dimple.project.log.service.LoginLogService;
import com.dimple.project.log.service.OperateLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @className: AsyncFactory
 * @description: 异步工厂（产生任务用）
 * @author: Dimple
 * @date: 10/22/19
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLoginLog(final String username, final String status, final String message,
                                           final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                LoginLog loginLog = new LoginLog();
                loginLog.setUserName(username);
                loginLog.setIp(ip);
                loginLog.setLocation(address);
                loginLog.setBrowser(browser);
                loginLog.setOs(os);
                loginLog.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    loginLog.setStatus(true);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginLog.setStatus(false);
                }
                // 插入数据
                SpringUtils.getBean(LoginLogService.class).insertLoginLog(loginLog);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OperateLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setLocation(AddressUtils.getCityInfoByIp(operLog.getIp()));
                SpringUtils.getBean(OperateLogService.class).insertOperateLog(operLog);
            }
        };
    }
}
