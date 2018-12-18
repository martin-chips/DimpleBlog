package com.dimple.utils;

import org.springframework.context.MessageSource;

/**
 * @author : Owenb
 * @version : 1.0
 * @class : MessageUtil
 * @description :
 * @date : 12/06/18 14:19
 */
public class MessageUtil {
    public static String getMessage(String code, Object... args) {
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, null);
    }
}
