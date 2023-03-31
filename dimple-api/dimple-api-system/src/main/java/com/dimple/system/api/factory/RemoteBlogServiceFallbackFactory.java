package com.dimple.system.api.factory;

import com.dimple.system.api.RemoteBlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteBlogServiceFallbackFactory implements FallbackFactory<RemoteBlogService> {
    @Override
    public RemoteBlogService create(Throwable cause) {
        log.error("日志服务调用失败:{}", cause.getMessage());
        return () -> null;

    }
}
