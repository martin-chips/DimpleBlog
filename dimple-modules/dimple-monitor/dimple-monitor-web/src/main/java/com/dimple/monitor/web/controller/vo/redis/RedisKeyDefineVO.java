package com.dimple.monitor.web.controller.vo.redis;

import com.dimple.common.redis.core.RedisKeyTypeEnum;
import com.dimple.common.redis.core.RedisTimeoutTypeEnum;
import com.dimple.common.redis.jsonSerializer.DurationToLongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

/**
 * RedisKeyDefineVO
 *
 * @author Dimple
 * @date 2/22/2023 2:48 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisKeyDefineVO {
    /**
     * Key 模板
     */
    private String keyTemplate;

    /**
     * Key 类型的枚举
     */
    private RedisKeyTypeEnum keyType;

    /**
     * Value 类型
     */
    private String valueType;

    /**
     * 超时类型
     */
    private RedisTimeoutTypeEnum timeoutType;

    /**
     * 过期时间，单位：毫秒
     */
    @JsonSerialize(using = DurationToLongSerializer.class)
    private Duration timeout;

    /**
     * 备注
     */
    private String mark;
}
