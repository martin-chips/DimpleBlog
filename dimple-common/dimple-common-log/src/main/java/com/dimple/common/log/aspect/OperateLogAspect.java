package com.dimple.common.log.aspect;

import com.alibaba.fastjson2.JSON;
import com.dimple.common.core.utils.ServletUtils;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.ip.IpUtils;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessStatus;
import com.dimple.common.log.service.AsyncLogService;
import com.dimple.common.security.utils.SecurityUtils;
import com.dimple.system.api.model.SysOperLogBO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 操作日志记录处理
 *
 * @author Dimple
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class OperateLogAspect {
    /**
     * 排除敏感属性字段
     */
    public static final String[] OPERATE_LOG_EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};
    private final AsyncLogService asyncLogService;

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, OperationLog controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OperationLog controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, OperationLog controllerLog, final Exception e, Object jsonResult) {
        try {
            // *========数据库日志=========*//
            SysOperLogBO operLog = new SysOperLogBO();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            operLog.setOperIp(ip);
            operLog.setOperUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
            String username = SecurityUtils.getUsername();
            if (StringUtils.isNotBlank(username)) {
                operLog.setOperName(username);
            }

            if (e != null) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // 保存数据库
            asyncLogService.saveSysLog(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, OperationLog log, SysOperLogBO operLog, Object jsonResult) throws Exception {
        // 设置action动作
        operLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            operLog.setOperParam(LogAspectUtils.getRequestParams(joinPoint, operLog.getRequestMethod(), OPERATE_LOG_EXCLUDE_PROPERTIES));
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult)) {
            operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }


}
