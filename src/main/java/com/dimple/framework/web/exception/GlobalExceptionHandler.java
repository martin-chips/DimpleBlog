package com.dimple.framework.web.exception;

import com.dimple.common.exception.BusinessException;
import com.dimple.common.exception.DemoModeException;
import com.dimple.common.utils.ServletUtils;
import com.dimple.common.utils.security.PermissionUtils;
import com.dimple.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @className: GlobalExceptionHandler
 * @description: 全局异常处理器
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 权限校验失败 如果请求为ajax返回json，普通请求跳转页面
     */
    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(HttpServletRequest request, AuthorizationException e) {
        log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request)) {
            return AjaxResult.error(PermissionUtils.getMsg(e.getMessage()) + ",日志标识： " + System.currentTimeMillis());
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/error/unauth");
            return modelAndView;
        }
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public AjaxResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("不支持' " + e.getMethod() + "'请求" + ",日志标识： " + System.currentTimeMillis());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public AjaxResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return AjaxResult.error("运行时异常:" + e.getMessage() + ",日志标识： " + System.currentTimeMillis());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("服务器错误，请联系管理员" + ",日志标识： " + System.currentTimeMillis());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public AjaxResult businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage() + ",日志标识： " + System.currentTimeMillis());
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult demoModeException(DemoModeException e) {
        return AjaxResult.error("演示模式，不允许操作" + ",日志标识： " + System.currentTimeMillis());
    }
}
