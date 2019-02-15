package com.dimple.framework.exception;

import com.dimple.framework.exception.user.UserException;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.utils.PermissionUntil;
import com.dimple.utils.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName: DefaultExceptionHandler
 * @Description: 统一异常处理
 * @Auther: Owenb
 * @Date: 12/06/18 12:40
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 请求方式不支持异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(500, "不支持\"" + e.getMethod() + "\"请求");
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResultUtil.error(500, "运行时异常:" + e.getMessage());
    }

    /**
     * 服务器错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result systemException(Exception e) {
        log.error("服务器错误:" + e.getMessage(), e);
        return ResultUtil.error(11, "服务器错误");
    }

    /**
     * 权限校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    public Object unauthorizedException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage());
        //判断下当前用户的访问环境
        if (ServletUtil.isAjaxRequest(request)) {
            return ResultUtil.error(11, PermissionUntil.getMsg(e.getMessage()));
        } else {
            return new ModelAndView("common/403");
        }
    }



    @ExceptionHandler(UserException.class)
    public Result userException(Exception e) {
        log.error("用户认证异常:" + e.getMessage(), e);
        return ResultUtil.error(11, e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public Result ioException(Exception e) {
        log.error("IO异常:" + e.getMessage(), e);
        return ResultUtil.error(11, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result methodArgumentTypeMismatchException(Exception e) {
        log.error("参数转换异常：" + e.getMessage(), e);
        return ResultUtil.error(11, e.getMessage());
    }


    @ExceptionHandler(UnknownSessionException.class)
    public Object unknownSessionException(Exception e) {
        log.error("Session异常：" + e.getMessage(), e);
        return new ModelAndView("common/500");
    }
}
