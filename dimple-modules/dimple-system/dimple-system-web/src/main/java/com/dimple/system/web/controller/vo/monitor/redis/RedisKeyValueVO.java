package com.dimple.system.web.controller.vo.monitor.redis;

import io.swagger.v3.oas.annotations.media.Schema;
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
