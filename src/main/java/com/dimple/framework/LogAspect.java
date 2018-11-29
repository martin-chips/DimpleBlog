package com.dimple.framework;

import com.alibaba.fastjson.JSONObject;
import com.dimple.bean.OperatorLog;
import com.dimple.bean.User;
import com.dimple.constant.Status;
import com.dimple.framework.annotation.Log;
import com.dimple.service.OperatorLogService;
import com.dimple.utils.AddressUtil;
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
import java.util.Date;
import java.util.Map;

/**
 * @ClassName: LogAspect
 * @Description:
 * @Auther: Owenb
 * @Date: 11/29/18 10:34
 * @Version: 1.0
 */
@Aspect
@Component
@EnableAsync
@Slf4j
public class LogAspect {

    @Autowired
    OperatorLogService operatorLogService;


    /**
     * 日志记录的切入点
     */
    @Pointcut("@annotation(com.dimple.framework.annotation.Log)")
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
     * @param e         异常信息
     */
    @Async
    public void handleLog(JoinPoint joinPoint, Exception e) {
        Log controllerLog = getAnnotationLog(joinPoint);
        if (controllerLog == null) {
            return;
        }
        //获取当前用户
        User user = ShiroUtil.getUser();
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setStatus(Status.SUCCESS);
        String ip = ShiroUtil.getIp();
        //设置IP地址
        operatorLog.setOperIp(ip);
        operatorLog.setOperLocation(AddressUtil.getRealAddressByIP(ip));
        //设置请求的URL
        operatorLog.setOperUrl(ServletUtil.getRequest().getRequestURI());
        if (user != null) {
            operatorLog.setOperName(user.getUserName());
        }
        //设置异常处理
        if (e != null) {
            operatorLog.setStatus(Status.Failure);
            operatorLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 255));
        }
        //设置方法名称(未采用getSimpleName的方法)
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        operatorLog.setMethod(className + "." + methodName + "()");
        setControllerMethodDescription(operatorLog, controllerLog);
        operatorLog.setOperTime(new Date());
        operatorLogService.insertOperatorLog(operatorLog);
    }

    /**
     * 将Controller注解中的参数拿到，设置到Log对象中去
     *
     * @param operatorLog   log对象
     * @param controllerLog controller上注解标注的对象
     */
    private void setControllerMethodDescription(OperatorLog operatorLog, Log controllerLog) {
        operatorLog.setAction(controllerLog.action());
        operatorLog.setTitle(controllerLog.title());
        operatorLog.setChannel(controllerLog.channel());
        //设置参数保存（比如密码这些就可以不用显示直接设置为false）
        if (controllerLog.isSaveRequestData()) {
            Map<String, String[]> parameterMap = ServletUtil.getRequest().getParameterMap();
            String params = JSONObject.toJSONString(parameterMap);
            operatorLog.setOperParam(StringUtils.substring(params, 0, 255));
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
