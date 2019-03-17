package com.dimple.common.exception;

/**
 * @className: BusinessException
 * @description: 业务异常
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
