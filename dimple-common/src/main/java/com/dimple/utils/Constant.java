package com.dimple.utils;

/**
 * @className: Constant
 * @description: 常用静态常量
 * @author: Dimple
 * @date: 06/17/20
 */
public class Constant {

    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    /**
     * 常用接口
     */
    public static class Url {
        // 免费图床
        public static final String SM_MS_URL = "https://sm.ms/api";
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }
}
