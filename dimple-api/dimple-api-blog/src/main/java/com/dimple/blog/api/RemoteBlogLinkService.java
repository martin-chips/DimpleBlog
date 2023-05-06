package com.dimple.blog.api;

import com.dimple.blog.api.bo.BlogLinkBO;
import com.dimple.blog.api.factory.RemoteBlogLinkServiceFallbackFactory;
import com.dimple.blog.api.model.BlogLinkDTO;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RemoteBlogLinkService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogLinkService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogLinkServiceFallbackFactory.class)
public interface RemoteBlogLinkService {
    @PostMapping("link/inner")
    ResponseEntity<Integer> insertBlogLink(@RequestBody BlogLinkBO blogLink, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("link/inner/list")
    ResponseEntity<List<BlogLinkBO>> selectBlogLinkList(@RequestBody BlogLinkDTO blogLinkDTO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PutMapping("link/inner/visitCount/{id}")
    ResponseEntity<Integer> addLinkVisitCount(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
