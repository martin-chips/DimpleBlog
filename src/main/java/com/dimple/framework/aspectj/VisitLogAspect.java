package com.dimple.framework.aspectj;

import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.manager.AsyncManager;
import com.dimple.framework.manager.factory.AsyncFactory;
import com.dimple.project.log.domain.VisitLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @className: VisitLogAspect
 * @description:
 * @author: Dimple
 * @date: 2019/11/30
 */
@Aspect
@Component
@Slf4j
public class VisitLogAspect {

    @Pointcut("@annotation(com.dimple.framework.aspectj.lang.annotation.VLog)")
    public void logPointCut() {
    }

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            // 获得注解
            VLog vLog = getAnnotationLog(joinPoint);
            if (vLog == null) {
                return;
            }
            VisitLog visitLog = new VisitLog();
            visitLog.setPageId(getPageId(vLog, joinPoint));
            visitLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
            visitLog.setUrl(ServletUtils.getRequest().getRequestURI());
            if (e != null) {
                visitLog.setStatus(false);
                visitLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            } else {
                visitLog.setStatus(true);
            }
            //设置入口地址
            visitLog.setEntryUrl(ServletUtils.getRequest().getHeader("referer"));
            // 处理设置注解上的参数
            getControllerMethodDescription(vLog, visitLog);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordVisitLog(visitLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage(), exp);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log      日志
     * @param visitLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(VLog log, VisitLog visitLog) {
        visitLog.setTitle(log.title());
    }


    /**
     * 是否存在注解，如果存在就获取
     */
    private VLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(VLog.class);
        }
        return null;
    }

    /**
     * 获取PageId
     *
     * @param joinPoint 切入点
     * @return PageId
     */
    private Long getPageId(VLog vLog, JoinPoint joinPoint) throws NoSuchMethodException {
        String pageIdStr = vLog.pageId();
        if (StringUtils.isEmpty(pageIdStr)) {
            return null;
        }
        //get target method
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getMethod(methodSignature.getMethod().getName(), methodSignature.getMethod().getParameterTypes());
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
}
