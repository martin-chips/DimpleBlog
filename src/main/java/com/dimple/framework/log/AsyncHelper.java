package com.dimple.framework.log;

import com.dimple.modules.BackStageModule.LogManager.bean.LoginLog;
import com.dimple.modules.BackStageModule.LogManager.bean.OperateLog;
import com.dimple.modules.BackStageModule.VisitorManager.bean.Visitor;
import com.dimple.modules.BackStageModule.LogManager.service.LoginLogService;
import com.dimple.modules.BackStageModule.LogManager.service.OperateLogService;
import com.dimple.modules.BackStageModule.VisitorManager.service.VisitorService;
import com.dimple.utils.*;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimerTask;

/**
 * @className: AsyncHelper
 * @description: 异步帮助类，执行异步
 * @auther: Dimple
 * @date: 01/14/19 11:37
 * @version: 1.0
 */
@Slf4j
public class AsyncHelper {

    /**
     * 记录操作日志
     *
     * @param operateLog
     * @return
     */
    public static TimerTask recordOperateLog(OperateLog operateLog) {
        return new TimerTask() {
            @Override
            public void run() {
                operateLog.setOperateLocation(AddressUtil.getRealAddressByIP(operateLog.getOperateIp()));
                //设置时间
                operateLog.setOperateTime(new Date());
                log.info(operateLog.toString());
                SpringUtil.getBean(OperateLogService.class).insertOperatorLog(operateLog);
            }
        };
    }

    /**
     * 记录登录登出日志
     *
     * @param loginId
     * @param status
     * @param msg
     * @param args
     * @return
     */
    public static TimerTask recordLoginLog(String loginId, Boolean status, String msg, Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        final String ip = IpUtil.getAccessIp();
        return new TimerTask() {
            @Override
            public void run() {
                //获取操作系统
                String os = userAgent.getOperatingSystem().getName();
                //获取浏览器类型
                String browser = userAgent.getBrowser().getName();
                LoginLog loginLog = new LoginLog();
                loginLog.setLoginName(loginId);
                loginLog.setStatus(status);
                //设置IP地理位置
                loginLog.setLoginLocation(AddressUtil.getRealAddressByIP(ip));
                loginLog.setBrowser(browser);
                loginLog.setOs(os);
                loginLog.setMsg(msg);
                //设置时间戳
                loginLog.setLoginTime(new Date());
                log.info(loginLog.toString());
                SpringUtil.getBean(LoginLogService.class).insertLoginLog(loginLog);
            }
        };
    }


    public static TimerTask recordVisitorLog(Visitor visitor) {

        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        final String ip = IpUtil.getAccessIp();

        //获取爬虫类型
        final String spider = SpiderUtil.parseUserAgent(ServletUtil.getUserAgent());
        return new TimerTask() {
            @Override
            public void run() {
                visitor.setIp(ip);
                visitor.setVisitTime(new Date());
                visitor.setSpider(spider);
                String address = AddressUtil.getRealAddressByIP(ip);
                visitor.setAddress(address);
                //获取操作系统
                String os = userAgent.getOperatingSystem().getName();
                //获取浏览器类型
                String browser = userAgent.getBrowser().getName();
                visitor.setBrowser(browser);
                visitor.setOs(os);
                SpringUtil.getBean(VisitorService.class).insertVisitorLog(visitor);
            }
        };
    }


    private static void saveLoginLog(String loginName, String msg, Boolean status) {

    }

}
