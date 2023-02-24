package com.dimple.monitor.web.controller.vo.redis;

import lombok.Data;

import java.util.List;
import java.util.Properties;

/**
 * RedisMonitorInfo
 *
 * @author Dimple
 * @date 2/22/2023 2:34 PM
 */
@Data
public class RedisMonitorInfoVO {

    /**
     * Redis info 指令结果,具体字段，查看 Redis 文档
     */
    private Properties info;

    /**
     * Redis key 数量
     */
    private Long dbSize;

    private List<RedisCommandVO> commandStats;

}
