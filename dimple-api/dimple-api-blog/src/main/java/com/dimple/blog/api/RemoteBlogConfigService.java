package com.dimple.blog.api;

import com.dimple.blog.api.bo.config.BlogGlobalConfig;
import com.dimple.blog.api.factory.RemoteBlogConfigServiceFallbackFactory;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * RemoteBlogGlobalConfigService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogGlobalConfigService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogConfigServiceFallbackFactory.class)
public interface RemoteBlogConfigService {
    @GetMapping(value = "/config/global")
    ResponseEntity<BlogGlobalConfig> getBlogGlobalConfig(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
