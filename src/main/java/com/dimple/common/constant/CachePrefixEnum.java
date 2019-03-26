package com.dimple.common.constant;

/**
 * @className: CachePrefixEnum
 * @description: 缓存前缀枚举类
 * @auther: Dimple
 * @date: 01/14/19 21:00
 * @version: 1.0
 */
public enum CachePrefixEnum {

    /**
     * 爬虫类型
     */
    SPIDER("spider_cache_"),
    ;

    private String prefix;

    CachePrefixEnum(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
