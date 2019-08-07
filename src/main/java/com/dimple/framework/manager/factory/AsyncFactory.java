package com.dimple.framework.manager.factory;

import com.dimple.common.constant.Constants;
import com.dimple.common.utils.AddressUtils;
import com.dimple.common.utils.LogUtils;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.SpiderUtils;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.blog.comment.domain.Comment;
import com.dimple.project.blog.comment.service.CommentService;
import com.dimple.project.log.logininfor.domain.Logininfor;
import com.dimple.project.log.logininfor.service.LogininforServiceImpl;
import com.dimple.project.log.operlog.domain.OperLog;
import com.dimple.project.log.operlog.service.IOperLogService;
import com.dimple.project.log.visitorLog.domain.VisitLog;
import com.dimple.project.log.visitorLog.service.VisitLogService;
import com.dimple.project.monitor.online.domain.OnlineSession;
import com.dimple.project.monitor.online.domain.UserOnline;
import com.dimple.project.monitor.online.service.IUserOnlineService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * @className: AsyncFactory
 * @description: 异步工厂（产生任务用）
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Slf4j
public class AsyncFactory {

    /**
     * 同步session到数据库
     *
     * @param session 在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                UserOnline online = new UserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                online.setSession(session);
                SpringUtils.getBean(IUserOnlineService.class).saveOnline(online);

            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(IOperLogService.class).insertOperlog(operLog);
            }
        };
    }

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();
        return new TimerTask() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(AddressUtils.getRealAddressByIP(ip));
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                log.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                Logininfor logininfor = new Logininfor();
                logininfor.setLoginName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(LogininforServiceImpl.class).insertLogininfor(logininfor);
            }
        };
    }

    public static TimerTask recordVisitLog(VisitLog visitLog) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        //获取爬虫类型
        final String spider = SpiderUtils.parseUserAgent(ServletUtils.getUserAgent());
        return new TimerTask() {
            @Override
            public void run() {
                visitLog.setOs(userAgent.getOperatingSystem().getName());
                visitLog.setSpider(spider);
                visitLog.setBrowser(userAgent.getBrowser().getName());
                visitLog.setLocation(AddressUtils.getRealAddressByIP(visitLog.getIpAddr()));
                visitLog.setCreateBy(ShiroUtils.getSessionId());
                SpringUtils.getBean(VisitLogService.class).insertVisitLog(visitLog);
            }
        };
    }

    /**
     * 异步插入评论
     *
     * @param comment 评论信息
     * @return
     */
    public static TimerTask recordCommentInfo(Comment comment) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        return new TimerTask() {
            @Override
            public void run() {
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                comment.setOs(os);
                comment.setBrowser(browser);
                comment.setLocation(AddressUtils.getRealAddressByIP(comment.getIp()));
                SpringUtils.getBean(CommentService.class).insertComment(comment);
            }
        };

    }
}
