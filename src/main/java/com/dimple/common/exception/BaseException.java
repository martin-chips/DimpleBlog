package com.dimple.common.exception;

import com.dimple.common.utils.MessageUtils;
import com.dimple.common.utils.StringUtils;

/**
 * @className: BaseException
 * @description: the base of exception
 * @author: Dimple
 * @date: 10/22/19
 */
public class BaseException extends RuntimeException {

    /**
     * the module
     */
    private String module;

    /**
     * error code
     */
    private String code;

    /**
     * the args
     */
    private Object[] args;

    /**
     * error message
     */
    private String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
