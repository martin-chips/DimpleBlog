package com.dimple.framework.log;

import com.alibaba.fastjson.JSONObject;
import com.dimple.bean.OperateLog;
import com.dimple.bean.User;
import com.dimple.framework.constant.Status;
import com.dimple.framework.log.annotation.Log;
import com.dimple.service.OperateLogService;
import com.dimple.utils.IpUtil;
import com.dimple.utils.ServletUtil;
import com.dimple.utils.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName: LogAspect
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 10:34
 * @Version: 1.0
 */
@Component
@Slf4j
public class LogAspect {

    @Autowired
    OperateLogService operateLogService;

    @Autowired
    AsyncLog asyncLog;

    /**
     * 日志记录的切入点
     */
    @Pointcut("@annotation(com.dimple.framework.log.annotation.Log)")
    public void logPointcut() {

    }

    /**
     * 在方法执行完毕之后执行
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "logPointcut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 出异常后执行的方法
     *
     * @param joinPoint 切入点
     * @param e         异常
     */
    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    /**
     * 处理日志信息
     *
     * @param joinPoint 切入点对象
     * @param exception 异常信息
     */
    public void handleLog(JoinPoint joinPoint, Exception exception) {
        Log annotationLog = getAnnotationLog(joinPoint);
        if (annotationLog == null) {
            return;
        }
        //获取当前用户
        User user = ShiroUtil.getUser();
        //日志
        OperateLog operateLog = new OperateLog();
        //请求的地址
        String ip = IpUtil.getAccessIp();
        operateLog.setOperateIp(ip);
        //设置请求的地址
        operateLog.setOperateUrl(ServletUtil.getRequest().getRequestURI());
        //设置操作的人员
        if (user != null) {
            operateLog.setOperatorName(user.getUserLoginId());
        }
        //设置是否异常
        if (exception != null) {
            operateLog.setOperateStatus(Status.FAILURE);
            operateLog.setErrorMsg(StringUtils.substring(exception.getMessage(), 0, 2000));
        } else {
            operateLog.setOperateStatus(Status.SUCCESS);
        }
        //设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        operateLog.setMethod(className + "." + methodName + "()");
        //处理注解上的参数
        setControllerMethodDescription(operateLog, annotationLog);
        asyncLog.recordOperateLog(operateLog);
    }

    /**
     * 将Controller注解中的参数拿到，设置到Log对象
     *
     * @param operateLog    log对象
     * @param annotationLog controller上注解标注的对象
     */
    private void setControllerMethodDescription(OperateLog operateLog, Log annotationLog) {
        operateLog.setOperateType(annotationLog.operateType().getType());
        operateLog.setTitle(annotationLog.title());
        //设置参数保存（比如密码这些就可以不用显示直接设置为false）
        if (annotationLog.isSaveRequestData()) {
            Map<String, String[]> parameterMap = ServletUtil.getRequest().getParameterMap();
            String params = JSONObject.toJSONString(parameterMap);
            //截取参数
            operateLog.setOperateParam(StringUtils.substring(params, 0, params.length() < 255 ? params.length() : 255));
        }
    }

    /**
     * 查询这个controller上面是不是有一个注解
     *
     * @param joinPoint
     * @return
     */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取方法
        Method method = methodSignature.getMethod();
        //返回
        return method == null ? null : method.getAnnotation(Log.class);
    }
}
