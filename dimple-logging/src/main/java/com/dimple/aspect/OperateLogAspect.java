package com.dimple.aspect;

import com.dimple.domain.OperateLog;
import com.dimple.service.OperateLogService;
import com.dimple.utils.IpUtil;
import com.dimple.utils.RequestHolder;
import com.dimple.utils.SecurityUtils;
import com.dimple.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: OperateLogAspect
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Component
@Aspect
@Slf4j
public class OperateLogAspect {

    private final OperateLogService operateLogService;

    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    public OperateLogAspect(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.dimple.annotation.OLog)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
        OperateLog operateLog = new OperateLog("INFO", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        operateLogService.save(getUsername(), IpUtil.getBrowser(request), IpUtil.getIp(request), joinPoint, operateLog);
        return result;
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        OperateLog operateLog = new OperateLog("ERROR", System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        operateLog.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        operateLogService.save(getUsername(), IpUtil.getBrowser(request), IpUtil.getIp(request), (ProceedingJoinPoint) joinPoint, operateLog);
    }

    public String getUsername() {
        try {
            return SecurityUtils.getCurrentUsername();
        } catch (Exception e) {
            log.error("can not get current username ,[{}],{}", e.getMessage(), e);
            return "";
        }
    }
}
