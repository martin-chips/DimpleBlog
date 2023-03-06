package com.dimple.common.log.aspect;

import com.alibaba.fastjson2.JSON;
import com.dimple.common.core.utils.ServletUtils;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.ip.IpUtils;
import com.dimple.common.log.annotation.VisitLog;
import com.dimple.common.log.enums.BusinessStatus;
import com.dimple.common.log.service.AsyncLogService;
import com.dimple.system.api.model.BlogVisitLogBO;
import io.netty.util.internal.ThrowableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * VisitLogAspect
 *
 * @author Dimple
 * @date 3/6/2023 7:37 PM
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class VisitLogAspect {
    private final AsyncLogService asyncLogService;
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};


    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(visitLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, VisitLog visitLog, Object jsonResult) {
        handleLog(joinPoint, visitLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(visitLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, VisitLog visitLog, Exception e) {
        handleLog(joinPoint, visitLog, e, null);
    }

    private void handleLog(final JoinPoint joinPoint, VisitLog visitLog, final Exception e, Object jsonResult) {
        BlogVisitLogBO blogVisitLogBO = new BlogVisitLogBO();
        try {
            blogVisitLogBO.setStatusCode(BusinessStatus.SUCCESS.ordinal());
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            blogVisitLogBO.setIp(ip);
            blogVisitLogBO.setRequestUri(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
            String controllerName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            blogVisitLogBO.setControllerName(controllerName);
            blogVisitLogBO.setMethodName(methodName);
            blogVisitLogBO.setRequestMethod(ServletUtils.getRequest().getMethod());
            setControllerMethodDescription(joinPoint, visitLog, blogVisitLogBO, jsonResult);
            String userAgent = ServletUtils.getRequest().getHeader("User-Agent");
            String referer = ServletUtils.getRequest().getHeader("Referer");
            blogVisitLogBO.setUserAgent(userAgent);
            blogVisitLogBO.setReferer(referer);
            if (!Objects.isNull(e)) {
                blogVisitLogBO.setStatusCode(BusinessStatus.FAIL.ordinal());
                blogVisitLogBO.setException(ThrowableUtil.stackTraceToString(e).substring(0, 2000));
            }
            asyncLogService.saveVisitLog(blogVisitLogBO);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
    }


    private Long getPageId(final JoinPoint joinPoint, VisitLog visitLog) {
        String pageIdStr = visitLog.pageId();
        if (StringUtils.isEmpty(pageIdStr)) {
            return null;
        }
        //get target method
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = null;
        try {
            method = joinPoint.getTarget().getClass().getMethod(methodSignature.getMethod().getName(), methodSignature.getMethod().getParameterTypes());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
        //express SpEL
        ExpressionParser expressionParser = new SpelExpressionParser();
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] params = localVariableTableParameterNameDiscoverer.getParameterNames(method);

        Object[] args = joinPoint.getArgs();
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }

        Expression expression = expressionParser.parseExpression(pageIdStr);
        Object value = expression.getValue(context);

        if (value == null) {
            return null;
        }
        try {
            return (Long) value;
        } catch (Exception e) {
            log.error("get pageId error for parameters {}", value);
            return null;
        }
    }

    public void setControllerMethodDescription(JoinPoint joinPoint, VisitLog log, BlogVisitLogBO blogVisitLogBO, Object jsonResult) throws Exception {
        String title = log.title();
        blogVisitLogBO.setTitle(title);
        blogVisitLogBO.setPageId(getPageId(joinPoint, log));
        if (log.saveRequestData()) {
            blogVisitLogBO.setRequestParams(LogAspectUtils.getRequestParams(joinPoint, blogVisitLogBO.getRequestMethod(), EXCLUDE_PROPERTIES));
        }

        if (log.saveResponseData() && StringUtils.isNotNull(jsonResult)) {
            blogVisitLogBO.setResponseParams(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }
}
