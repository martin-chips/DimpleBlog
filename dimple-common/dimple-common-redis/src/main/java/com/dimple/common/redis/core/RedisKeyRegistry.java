package com.dimple.common.redis.core;

import java.util.ArrayList;
import java.util.List;

/**
 * RedisKeyRegistry
 *
 * @author Dimple
 * @date 2/22/2023 2:51 PM
 */
public class RedisKeyRegistry {
    /**
     * Redis RedisKeyDefine 数组
     */
    private static final List<RedisKeyDefine> DEFINES = new ArrayList<>();

    public static void add(RedisKeyDefine define) {
        DEFINES.add(define);
    }

    public static List<RedisKeyDefine> list() {
        return DEFINES;
    }

    public static int size() {
        return DEFINES.size();
    }
}
