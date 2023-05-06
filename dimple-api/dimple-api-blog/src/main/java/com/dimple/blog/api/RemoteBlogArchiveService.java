package com.dimple.blog.api;

import com.dimple.blog.api.bo.BlogArchiveItemBO;
import com.dimple.blog.api.bo.BlogArchivesBO;
import com.dimple.blog.api.factory.RemoteBlogArchiveServiceFallbackFactory;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * RemoteBlogArchiveService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogArchiveService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogArchiveServiceFallbackFactory.class)
public interface RemoteBlogArchiveService {
    @GetMapping(value = "/archive/inner/archives")
    ResponseEntity<List<BlogArchiveItemBO>> getArchives(@RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping(value = "/archive/inner/list")
    ResponseEntity<List<BlogArchivesBO>> getArchiveList(@RequestParam("countType") String countType, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
