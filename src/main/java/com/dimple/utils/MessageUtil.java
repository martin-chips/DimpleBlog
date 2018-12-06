package com.dimple.utils;

import org.springframework.context.MessageSource;

/**
 * @ClassName: MessageUtil
 * @Description:
 * @Auther: Owenb
 * @Date: 12/06/18 14:19
 * @Version: 1.0
 */
public class MessageUtil {
    public static String getMessage(String code, Object... args) {
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, null);
    }
}
