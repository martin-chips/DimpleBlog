package com.dimple.framework.manager.factory;

import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.SpiderUtils;
import com.dimple.common.utils.ip.AddressUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.common.domain.ReplayEmail;
import com.dimple.project.common.service.EmailService;
import com.dimple.project.log.domain.LoginLog;
import com.dimple.project.log.domain.OperateLog;
import com.dimple.project.log.domain.VisitLog;
import com.dimple.project.log.service.LoginLogService;
import com.dimple.project.log.service.OperateLogService;
import com.dimple.project.log.service.VisitLogService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * @className: AsyncFactory
 * @description: async factory
 * @author: Dimple
 * @date: 10/22/19
 */
@Slf4j
public class AsyncFactory {
    private AsyncFactory() {

    }

    /**
     * record login log
     *
     * @param username user name
     * @param status   status
     * @param message  message
     * @param args     args
     * @return timeTask
     */
    public static TimerTask recordLoginLog(final String username, final Boolean status, final String message,
                                           final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getCityInfoByIp(ip);
                String os = userAgent.getOperatingSystem().getName();
                String browser = userAgent.getBrowser().getName();
                LoginLog loginLog = new LoginLog();
                loginLog.setUserName(username);
                loginLog.setIp(ip);
                loginLog.setBrowser(browser);
                loginLog.setOs(os);
                loginLog.setMsg(message);
                loginLog.setLocation(address);
                loginLog.setStatus(status);
                log.info("insert login log {}", loginLog);
                SpringUtils.getBean(LoginLogService.class).insertLoginLog(loginLog);
            }
        };
    }

    /**
     * record operate log
     *
     * @param operateLog operate log
     * @return timeTask
     */
    public static TimerTask recordOperateLog(final OperateLog operateLog) {
        return new TimerTask() {
            @Override
            public void run() {
                operateLog.setLocation(AddressUtils.getCityInfoByIp(operateLog.getIp()));
                SpringUtils.getBean(OperateLogService.class).insertOperateLog(operateLog);
            }
        };
    }

    /**
     * record visit log
     *
     * @param visitLog visitLog
     * @return timeTask
     */
    public static TimerTask recordVisitLog(final VisitLog visitLog) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String spider = SpiderUtils.parseUserAgent(ServletUtils.getUserAgent());
        return new TimerTask() {
            @Override
            public void run() {
                visitLog.setOs(userAgent.getOperatingSystem().getName());
                visitLog.setSpider(spider);
                visitLog.setBrowser(userAgent.getBrowser().getName());
                visitLog.setLocation(AddressUtils.getCityInfoByIp(visitLog.getIp()));
                SpringUtils.getBean(VisitLogService.class).insertVisitLog(visitLog);
            }
        };
    }

    public static TimerTask sendReplyEmail(String url, String htmlContent, String nickName, String email, ReplayEmail replayEmail) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(EmailService.class).sendReplyEmail(url, htmlContent, nickName, email,replayEmail);
            }
        };
    }
}
