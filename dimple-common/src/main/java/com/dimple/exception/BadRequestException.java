package com.dimple.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @className: BadRequestException
 * @description: 统一异常处理
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
public class BadRequestException extends RuntimeException {

    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }
}
