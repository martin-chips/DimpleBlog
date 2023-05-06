package com.dimple.blog.api.factory;

import com.dimple.blog.api.RemoteBlogConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Blog服务降级处理
 *
 * @author Dimple
 */
@Component
@Slf4j
public class RemoteBlogConfigServiceFallbackFactory implements FallbackFactory<RemoteBlogConfigService> {
    @Override
    public RemoteBlogConfigService create(Throwable cause) {
        log.error("博客服务调用失败:{}", cause.getMessage());
        return source -> null;
    }
}
