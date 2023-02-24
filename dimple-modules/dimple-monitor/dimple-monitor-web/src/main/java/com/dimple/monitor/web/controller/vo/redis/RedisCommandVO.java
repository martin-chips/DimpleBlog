package com.dimple.monitor.web.controller.vo.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RedisCommandVO
 *
 * @author Dimple
 * @date 2/22/2023 2:35 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisCommandVO {
    /**
     * Redis 命令
     */
    private String command;

    /**
     * 调用次数
     */
    private Long calls;

    /**
     * 消耗 CPU 秒数
     */
    private Long usec;
}
