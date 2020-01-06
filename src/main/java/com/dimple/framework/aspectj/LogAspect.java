package com.dimple.framework.aspectj;

import com.alibaba.fastjson.JSON;
import com.dimple.common.constant.Constants;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.ip.IpUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.manager.AsyncManager;
import com.dimple.framework.manager.factory.AsyncFactory;
import com.dimple.framework.security.LoginUser;
import com.dimple.framework.security.service.TokenService;
import com.dimple.project.log.domain.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @className: LogAspect
 * @description: the aspect for log operate log
 * @author: Dimple
 * @date: 10/22/19
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    private long startTime = 0L;

    @Pointcut("@annotation(com.dimple.framework.aspectj.lang.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * if the target method throw an exception will be blocked by this method.
     *
     * @param joinPoint join point
     * @param e         exception
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null, System.currentTimeMillis() - startTime);
    }

    /**
     * around method
     *
     * @param joinPoint join point
     * @return object(the target method result)
     * @throws Throwable exception
     */
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        handleLog(joinPoint, null, result, System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * for log record
     *
     * @param joinPoint  join point
     * @param e          exception
     * @param jsonResult result
     * @param cost       the time of this method cost
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult, long cost) {
        try {
            // get annotation
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            // get current user from servlet
            LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());

            OperateLog operateLog = new OperateLog();
            operateLog.setStatus(Constants.SUCCESS);
            // get the IP of this request
            operateLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
            // get result with JSON format
            operateLog.setJsonResult(JSON.toJSONString(jsonResult));
            operateLog.setCost(cost);
            operateLog.setUrl(ServletUtils.getRequest().getRequestURI());

            if (loginUser != null) {
                operateLog.setOperateName(loginUser.getUsername());
            }

            if (e != null) {
                operateLog.setStatus(Constants.FAILED);
                operateLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // get the class name
            String className = joinPoint.getTarget().getClass().getName();
            // get method name
            String methodName = joinPoint.getSignature().getName();
            operateLog.setMethod(MessageFormat.format("{}.{}()", className, methodName));
            // get request method
            operateLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // set method args
            getControllerMethodDescription(joinPoint, controllerLog, operateLog);
            // save log
            AsyncManager.me().execute(AsyncFactory.recordOperateLog(operateLog));
        } catch (Exception exception) {
            log.error("get exception in handleLog,{} ", exception.getMessage(), exception);
        }
    }

    /**
     * set the log <code>com.dimple.framework.aspectj.lang.annotation.Log</code> other descriptin
     *
     * @param joinPoint  join point
     * @param log        the annotation
     * @param operateLog the operateLog
     */
    private void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperateLog operateLog) {
        // set businessType
        operateLog.setBusinessType(log.businessType().ordinal());
        // set title
        operateLog.setTitle(log.title());
        // set operator type
        operateLog.setOperatorType(log.operatorType().ordinal());
        // save request data if saveRequestData is true
        if (log.isSaveRequestData()) {
            // set request value
            setRequestValue(joinPoint, operateLog);
        }
    }

    /**
     * get the data from request.
     * If this request method is PUT/POST get data from body(the method args)
     * else ,get data from attribute
     *
     * @param joinPoint  join point
     * @param operateLog operate log
     */
    private void setRequestValue(JoinPoint joinPoint, OperateLog operateLog) {
        String requestMethod = operateLog.getRequestMethod();
        if (HttpMethod.PUT.matches(requestMethod) || HttpMethod.POST.matches(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operateLog.setParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operateLog.setParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * get annotation of <code>com.dimple.framework.aspectj.lang.annotation.Log</code>
     *
     * @param joinPoint join point
     * @return the annotation of <code>com.dimple.framework.aspectj.lang.annotation.Log</code>,if can not find will return <code>null</code>
     */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     * concat params
     *
     * @param paramsArray data
     * @return params data
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params.append(jsonObj.toString() + " ");
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * consider if the data is file ,httpRequest or response
     *
     * @param o the data
     * @return if match return true,else return false
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}
