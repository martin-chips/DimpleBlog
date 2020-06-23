package com.dimple.aspect;

import com.dimple.domain.VisitLog;
import com.dimple.service.VisitLogService;
import com.dimple.utils.RequestHolder;
import com.dimple.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @className: VisitLogAspect
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Component
@Aspect
@Slf4j

public class VisitLogAspect {

    private final VisitLogService visitLogService;


    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    private VisitLogAspect(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @Pointcut("@annotation(com.dimple.annotation.VLog)")
    public void logPointCut() {
    }


    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        currentTime.set(System.currentTimeMillis());
        Object result = joinPoint.proceed();
        VisitLog visitLog = new VisitLog();
        visitLog.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        visitLogService.save(visitLog, joinPoint, RequestHolder.getHttpServletRequest());
        return result;
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
        VisitLog visitLog = new VisitLog();
        visitLog.setTime(System.currentTimeMillis() - currentTime.get());
        currentTime.remove();
        visitLog.setExceptionDetail(ThrowableUtil.getStackTrace(e).getBytes());
        visitLog.setStatus(false);
        visitLogService.save(visitLog, joinPoint, RequestHolder.getHttpServletRequest());
    }
}
