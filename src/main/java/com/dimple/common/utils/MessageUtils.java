package com.dimple.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import com.dimple.common.utils.spring.SpringUtils;

/**
 * @className: MessageUtils
 * @description: 获取i18n资源文件
 * @author: Dimple
 * @date: 10/22/19
 */
public class MessageUtils {
    private MessageUtils() {
    }

    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return 获取国际化翻译值
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
