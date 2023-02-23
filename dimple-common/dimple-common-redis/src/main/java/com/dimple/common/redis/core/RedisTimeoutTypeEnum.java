package com.dimple.common.redis.core;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RedisTimeoutTypeEnum
 *
 * @author Dimple
 * @date 2/22/2023 2:51 PM
 */
@Getter
@AllArgsConstructor
public enum RedisTimeoutTypeEnum {

    FOREVER(1), // 永不超时
    DYNAMIC(2), // 动态超时
    FIXED(3); // 固定超时

    /**
     * 类型
     */
    @JsonValue
    private final Integer type;

}