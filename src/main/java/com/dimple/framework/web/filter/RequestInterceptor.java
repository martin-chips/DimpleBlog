package com.dimple.framework.web.filter;

import com.dimple.common.utils.IpUtils;
import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.monitor.blacklist.domain.Blacklist;
import com.dimple.project.monitor.blacklist.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: RequestInterceptor
 * @description: 用户请求的拦截器
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
@Component
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    BlacklistService blacklistService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("当前访问的IP {},session id {}", IpUtils.getIpAddr(request), request.getSession().getId());

        Blacklist blacklist = blacklistService.selectBlacklistByIp(IpUtils.getIpAddr(request));

        if (blacklist != null) {
            log.warn("拦截到黑名单IP [{}]请求！", ShiroUtils.getIp());
            modelAndView.setViewName("/error/blacklist");
            blacklistService.updateNewestBlacklist(blacklist.getBlacklistId(), request.getRequestURI());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
