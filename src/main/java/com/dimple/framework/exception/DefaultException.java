package com.dimple.framework.exception;

/**
 * @className: DefaultException
 * @description:
 * @auther: Owenb
 * @date: 01/22/19 20:04
 * @version: 1.0
 */
public class DefaultException extends BaseException {
    public DefaultException(String module, String code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }
}
