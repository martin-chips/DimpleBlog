package com.dimple.blog.api;

import com.dimple.blog.api.bo.BlogArticleTagBO;
import com.dimple.blog.api.bo.BlogTagBO;
import com.dimple.blog.api.bo.BlogTagDTO;
import com.dimple.blog.api.factory.RemoteBlogTagServiceFallbackFactory;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RemoteBlogTagService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogTagService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogTagServiceFallbackFactory.class)
public interface RemoteBlogTagService {
    @GetMapping("tag/inner/{ids}")
    ResponseEntity<List<BlogTagBO>> selectBlogTagListByIds(@PathVariable("ids") List<Long> ids, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    @PostMapping("/tag/inner/list")
    ResponseEntity<List<BlogTagBO>> selectBlogTagList(@RequestBody BlogTagDTO blogTagDTO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);


    @GetMapping("tag/inner/article/{articleId}")
    ResponseEntity<List<BlogArticleTagBO>> selectBlogArticleTagByArticleId(@PathVariable("articleId") Long articleId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
