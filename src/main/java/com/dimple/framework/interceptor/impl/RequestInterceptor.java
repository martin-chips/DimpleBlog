package com.dimple.framework.interceptor.impl;

import com.alibaba.fastjson.JSONObject;
import com.dimple.common.constant.CacheConstant;
import com.dimple.common.utils.IpUtils;
import com.dimple.project.monitor.blacklist.domain.Blacklist;
import com.dimple.project.monitor.blacklist.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 请求处理之前调用（Controller方法之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("当前访问的IP {},当前访问入口地址：{}", IpUtils.getIpAddr(request), request.getHeader("referer"));
        if (request.getRequestURI().contains("error")) {
            return true;
        }
        if (checkIfInBlackList(request)) {
            response.sendRedirect("/error/blacklist");
            return false;
        }
        return true;
    }

    /**
     * 请求处理之后调用（controller调用方法之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 检查当前访问IP是不是在黑名单中
     *
     * @param request request
     * @return true:存在 ；false ：不存在
     */
    private boolean checkIfInBlackList(HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        //从Redis中获取黑名单
        String blackListCache = redisTemplate.opsForValue().get(CacheConstant.SYSTEM_CONFIG_CACHE_BLACKLIST_All);
        List<Blacklist> list = JSONObject.parseArray(blackListCache, Blacklist.class);
        if (Objects.isNull(list) || list.size() == 0) {
            List<Blacklist> blacklists = blacklistService.selectBlacklistList(new Blacklist());
            //设置保存一天的缓存
            redisTemplate.opsForValue().set(CacheConstant.SYSTEM_CONFIG_CACHE_BLACKLIST_All, JSONObject.toJSONString(blacklists), 1, TimeUnit.DAYS);
            return false;
        }
        Optional<Blacklist> any = list.stream().filter(blacklist -> blacklist.getIpAddr().equals(ip)).findAny();
        if (any.isPresent()) {
            Blacklist blacklist = any.get();
            log.warn("拦截到黑名单IP [{}]请求！", IpUtils.getIpAddr(request));
            blacklistService.updateNewestBlacklist(blacklist.getBlacklistId(), request.getRequestURI());
            return true;
        }
        return false;
    }
}
