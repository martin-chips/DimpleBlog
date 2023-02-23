package com.dimple.common.redis.core;

import lombok.Data;

import java.time.Duration;

/**
 * RedisKeyDefine
 *
 * @author Dimple
 * @date 2/22/2023 2:50 PM
 */
@Data
public class RedisKeyDefine {

    /**
     * Key 模板
     */
    private final String keyTemplate;
    /**
     * Key 类型的枚举
     */
    private final RedisKeyTypeEnum keyType;
    /**
     * Value 类型
     * <p>
     * 如果是使用分布式锁，设置为 {@link java.util.concurrent.locks.Lock} 类型
     */
    private final Class<?> valueType;
    /**
     * 超时类型
     */
    private final RedisTimeoutTypeEnum timeoutType;
    /**
     * 过期时间
     */
    private final Duration timeout;
    /**
     * 备注
     */
    private final String mark;

    private RedisKeyDefine(String mark, String keyTemplate, RedisKeyTypeEnum keyType, Class<?> valueType,
                           RedisTimeoutTypeEnum timeoutType, Duration timeout) {
        this.mark = mark;
        this.keyTemplate = keyTemplate;
        this.keyType = keyType;
        this.valueType = valueType;
        this.timeout = timeout;
        this.timeoutType = timeoutType;
        // 添加注册表
        RedisKeyRegistry.add(this);
    }

    public RedisKeyDefine(String mark, String keyTemplate, RedisKeyTypeEnum keyType, Class<?> valueType, Duration timeout) {
        this(mark, keyTemplate, keyType, valueType, RedisTimeoutTypeEnum.FIXED, timeout);
    }

    public RedisKeyDefine(String mark, String keyTemplate, RedisKeyTypeEnum keyType, Class<?> valueType, RedisTimeoutTypeEnum timeoutType) {
        this(mark, keyTemplate, keyType, valueType, timeoutType, Duration.ZERO);
    }

    /**
     * 格式化 Key
     * <p>
     * 注意，内部采用 {@link String#format(String, Object...)} 实现
     *
     * @param args 格式化的参数
     * @return Key
     */
    public String formatKey(Object... args) {
        return String.format(keyTemplate, args);
    }
}
