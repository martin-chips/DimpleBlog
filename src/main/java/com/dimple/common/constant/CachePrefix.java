package com.dimple.common.constant;

/**
 * @className: CachePrefix
 * @description: 缓存前缀枚举类
 * @auther: Dimple
 * @date: 01/14/19 21:00
 * @version: 1.0
 */
public class CachePrefix {
    /**
     * 爬虫列表
     */
    public static final String SPIDER = "spider_cache_";
    /**
     * 前台页面缓存的推荐分类
     */
    public static final String FRONT_SUPPORT_CATEGORIES = "front_categories_cache_";
    /**
     * 前台缓存的blog排行
     */
    public static final String FRONT_BLOG_RANKING = "front_blogRanking_cache_";
    /**
     * 前台缓存到最近更新的blog
     */
    public static final String FRONT_NEWEST_UPDATE_BLOG = "front_newest_update_blog_cache_";
    /**
     * 前台缓存的tag
     */
    public static final String FRONT_TAG = "front_tag_cache";
    /**
     * 获取前台的推荐博客
     */
    public static final String FRONT_BLOG_SUPPORT = "front_blog_support";
    /**
     * 缓存单个的blog
     */
    public static final String FRONT_BLOG_BLOG = "front_blog_blog";

    /**
     * 黑名单单个缓存
     */
    public static final String SYSTEM_BLACKLIST_ITEM = "system_blacklist_item";
}
