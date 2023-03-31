package com.dimple.system.api;

import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.system.api.factory.RemoteDictFallbackFactory;
import com.dimple.system.api.model.config.BlogGlobalConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * RemoteBlogGlobalConfigService
 *
 * @author BianXiaofeng
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogGlobalConfigService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteDictFallbackFactory.class)
public interface RemoteBlogService {
    @GetMapping(value = "/config/global")
    ResponseEntity<BlogGlobalConfig> getBlogGlobalConfig();
}
