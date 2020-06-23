package com.dimple.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: AsyncTaskProperties
 * @description: 线程池配置属性类
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "task.pool")
public class AsyncTaskProperties {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
