package com.dimple.system.api;

import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.system.api.factory.RemoteBlogServiceFallbackFactory;
import com.dimple.system.api.model.config.BlogGlobalConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * RemoteBlogGlobalConfigService
 *
 * @author BianXiaofeng
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogGlobalConfigService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogServiceFallbackFactory.class)
public interface RemoteBlogService {
    @GetMapping(value = "/config/global")
    ResponseEntity<BlogGlobalConfig> getBlogGlobalConfig(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
