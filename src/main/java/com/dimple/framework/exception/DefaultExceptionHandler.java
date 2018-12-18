package com.dimple.framework.exception;

import com.dimple.framework.exception.user.UserException;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
}
