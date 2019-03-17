package com.dimple.common.utils;

import org.springframework.context.MessageSource;
import com.dimple.common.utils.spring.SpringUtils;

/**
 * @className: MessageUtils
 * @description: 获取i18n资源文件
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class MessageUtils {
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, null);
    }
}
