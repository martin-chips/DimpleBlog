package com.dimple.system.web.controller.vo.monitor.redis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

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
