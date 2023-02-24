package com.dimple.monitor.web.controller.vo.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RedisKeyValueVO
 *
 * @author Dimple
 * @date 2/22/2023 3:12 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisKeyValueVO {
    private String key;

    private String value;
}
