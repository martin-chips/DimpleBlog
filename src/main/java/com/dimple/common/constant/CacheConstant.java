package com.dimple.common.constant;

/**
 * @className: CachePrefix
 * @description: 缓存前缀枚举类
 * @auther: Dimple
 * @date: 01/14/19 21:00
 * @version: 1.0
 */
public class CacheConstant {

    /**
     * 爬虫列表
     */
    public static final String BUSINESS_CACHE_SPIDER = "Spider";
    /**
     * 前台页面缓存的推荐分类
     */
    public static final String BUSINESS_CACHE_SUPPORT_CATEGORIES = "SupportCategories";
    /**
     * 前台缓存的blog排行
     */
    public static final String BUSINESS_CACHE_BLOG_RANKING = "BlogRanking";
    /**
     * 前台缓存到最近更新的blog
     */
    public static final String BUSINESS_CACHE_BLOG_NEWEST_UPDATE = "BlogNewestUpdate";
    /**
     * 获取前台的推荐博客
     */
    public static final String BUSINESS_CACHE_BLOG_SUPPORT = "BlogSupport";
    /**
     * 缓存单个的blog
     */
    public static final String BUSINESS_CACHE_BLOG_ITEM = "BlogItem";
    /**
     * 前台缓存的tag
     */
    public static final String BUSINESS_CACHE_TAG = "Tag";

    /**
     * 黑名单单个缓存
     */
    public static final String SYSTEM_CONFIG_CACHE_BLACKLIST_ITEM = "BlacklistItem";

    /**
     * 系統黑名单缓存
     */
    public static final String SYSTEM_CONFIG_CACHE_BLACKLIST_All = "BlacklistAll";
    /**
     * 百度AIToken
     */
    public static final String SYSTEM_CONFIG_CACHE_BAIDU_AI_ACCESS_TOKEN = "BaiduAccessToken";

    /**
     * 重复提交缓存
     */
    public static final String NO_REPEAT_SUBMIT = "NoRepeatSubmit";

}
