package com.dimple.common.exception;

import org.springframework.http.HttpStatus;

/**
 * @className: CustomException
 * @description: 自定义异常
 * @author: Dimple
 * @date: 10/22/19
 */
public class CustomException extends RuntimeException {

    private HttpStatus code;

    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, HttpStatus code) {
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

    public HttpStatus getCode() {
        return code;
    }
}
