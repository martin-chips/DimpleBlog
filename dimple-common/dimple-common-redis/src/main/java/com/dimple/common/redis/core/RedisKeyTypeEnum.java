package com.dimple.common.redis.core;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * KeyTypeEnum
 *
 * @author Dimple
 * @date 2/22/2023 2:51 PM
 */
@Getter
@AllArgsConstructor
public enum RedisKeyTypeEnum {

    STRING("String"),
    LIST("List"),
    HASH("Hash"),
    SET("Set"),
    ZSET("Sorted Set"),
    STREAM("Stream"),
    PUBSUB("Pub/Sub");

    /**
     * 类型
     */
    @JsonValue
    private final String type;

}