package com.dimple.common.security.utils;

import com.alibaba.fastjson2.JSONArray;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.core.utils.SpringUtils;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.redis.service.RedisService;
import com.dimple.system.api.model.SysDictDataBO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 字典工具类
 *
 * @author Dimple
 */
public class DictUtils {
    private DictUtils() {

    }

    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<SysDictDataBO> dictDatas) {
        SpringUtils.getBean(RedisService.class).setCacheObject(getCacheKey(key), dictDatas);
    }

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictDataBO> getDictCache(String key) {
        JSONArray arrayCache = SpringUtils.getBean(RedisService.class).getCacheObject(getCacheKey(key));
        if (StringUtils.isNotNull(arrayCache)) {
            return arrayCache.toList(SysDictDataBO.class);
        }
        return Collections.emptyList();
    }

    /**
     * 删除指定字典缓存
     *
     * @param key 字典键
     */
    public static void removeDictCache(String key) {
        SpringUtils.getBean(RedisService.class).deleteObject(getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(CacheConstants.SYS_DICT_KEY_DEFINE.formatKey("*"));
        SpringUtils.getBean(RedisService.class).deleteObject(keys);
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return CacheConstants.SYS_DICT_KEY_DEFINE.formatKey(configKey);
    }
}
