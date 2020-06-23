package com.dimple.exception;

/**
 * @className: BadConfigurationException
 * @description: 统一关于错误配置信息 异常
 * @author: Dimple
 * @date: 06/17/20
 */
public class BadConfigurationException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BadConfigurationException(String message) {
        super(message);
    }

}
