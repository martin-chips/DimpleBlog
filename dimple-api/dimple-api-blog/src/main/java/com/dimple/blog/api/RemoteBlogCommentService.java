package com.dimple.blog.api;

import com.dimple.blog.api.bo.BlogCommentBO;
import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.api.factory.RemoteBlogCommentServiceFallbackFactory;
import com.dimple.blog.api.model.BlogCommentDTO;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * RemoteBlogCommentService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogCommentService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogCommentServiceFallbackFactory.class)
public interface RemoteBlogCommentService {
    @PostMapping("comment/inner")
    ResponseEntity<Integer> insertBlogComment(@RequestBody BlogCommentBO blogComment, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("comment/inner/list")
    ResponseEntity<List<BlogCommentBO>> selectBlogCommentList(@RequestBody BlogCommentDTO blogCommentDTO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("comment/inner/{ids}")
    ResponseEntity<List<BlogCommentBO>> selectBlogCommentByParentIds(@PathVariable("ids") List<Long> ids, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PutMapping("comment/inner/count/{id}")
    ResponseEntity<Integer> addCommentLikeCount(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("comment/inner/count/{articleIds}")
    ResponseEntity<List<KeyValue<Long, Long>>> selectBlogCommentCountByArticleId(@PathVariable("articleIds") Set<Long> articleIds, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
