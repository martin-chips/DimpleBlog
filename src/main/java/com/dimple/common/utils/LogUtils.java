package com.dimple.common.utils;

/**
 * @className: LogUtils
 * @description: 处理并记录日志文件
 * @author: Dimple
 * @date: 10/22/19
 */
public class LogUtils {
    private LogUtils() {
    }

    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
