package com.dimple.framework.interceptor.impl;

import com.alibaba.fastjson.JSONObject;
import com.dimple.common.constant.CacheConstant;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.framework.interceptor.annotation.NoRepeatSubmit;
import com.dimple.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @className: NoRepeatSubmitInterceptor
 * @description: 重复提交拦截器
 * @auther: Dimple
 * @date: 07/23/19
 * @version: 1.0
 */
@Component
@Slf4j
public class NoRepeatSubmitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    StringRedisTemplate redisTemplate;

    private static final String REPEAT_PARAMS = "repeat_prams";

    public NoRepeatSubmitInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            NoRepeatSubmit methodAnnotation = handlerMethod.getMethodAnnotation(NoRepeatSubmit.class);
            if (Objects.nonNull(methodAnnotation) && isRepeatSubmit(request, methodAnnotation.intervalSecond())) {
                log.info("重复提交拦截 方法名：{},参数：{}", handlerMethod.getMethod().getName(), request.getParameterNames().toString());
                AjaxResult ajaxResult = AjaxResult.error("你的提交速度过快，请稍后再试！");
                ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

    /**
     * 判断是否是重复提交,如果不是重复提交，那么需要在Redis里面设置一个key
     *
     * @param request        request
     * @param intervalSecond 重复提交间隔时间
     * @return true：重复提交  false：非重复提交
     */
    private boolean isRepeatSubmit(HttpServletRequest request, int intervalSecond) {
        String nowParams = (request.getParameterMap() == null || request.getParameterMap().size() == 0) ? String.valueOf(System.currentTimeMillis()) : JSONObject.toJSONString(request.getParameterMap());
        //请求的SessionID+URI作为key保证不重复
        String key = CacheConstant.NO_REPEAT_SUBMIT + request.getRequestedSessionId() + request.getRequestURI();
        String value = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(value)) {
            String preParams = JSONObject.parseObject(value, String.class);
            if (preParams.equals(nowParams)) {
                return true;
            }
        }
        //将当前信息保存在Redis里面
        redisTemplate.opsForValue().set(key, nowParams, intervalSecond, TimeUnit.SECONDS);
        return false;
    }

    private boolean checkIfBeforeDeadLine(Map<String, Object> nowDataMap, Map preDataMap, int intervalSecond) {
        return false;
    }

    /**
     * 检查是否是相同的参数
     *
     * @param nowDataMap 当前数据
     * @param preDataMap 之前数据
     * @return 是否是相同参数
     */
    private boolean checkIfSameParams(Map<String, Object> nowDataMap, Map preDataMap) {
        return nowDataMap.get(REPEAT_PARAMS).equals(preDataMap.get(REPEAT_PARAMS));
    }


}
