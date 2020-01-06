package com.dimple.common.exception;

/**
 * @className: UtilException
 * @description: the exception with utils class use
 * @author: Dimple
 * @date: 10/22/19
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e.getMessage(), e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
