package com.dimple.utils;

/**
 * @ClassName: LogUtil
 * @Description: Log记录的通用工具类
 * @Auther: Owenb
 * @Date: 11/29/18 17:43
 * @Version: 1.0
 */
public class LogUtil {
    /**
     * 生成[message]的格式的工具类
     *
     * @param msg 需要生成的字符串
     * @return
     */
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

}
