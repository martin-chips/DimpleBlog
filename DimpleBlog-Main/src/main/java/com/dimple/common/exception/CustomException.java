package com.dimple.common.exception;

/**
 * @className: CustomException
 * @description: 自定义异常
 * @author: Dimple
 * @date: 10/22/19
 */
public class CustomException extends RuntimeException {

    private Integer code;

    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
