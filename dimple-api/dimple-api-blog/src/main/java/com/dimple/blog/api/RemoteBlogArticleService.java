package com.dimple.blog.api;

import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.api.factory.RemoteBlogArticleServiceFallbackFactory;
import com.dimple.blog.api.model.BlogArticleDTO;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * RemoteBlogArticleService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogArticleService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogArticleServiceFallbackFactory.class)
public interface RemoteBlogArticleService {

    @PostMapping("article/inner")
    ResponseEntity<List<BlogArticleBO>> selectBlogArticleList(@RequestBody BlogArticleDTO blogArticleDTO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("article/inner/{id}")
    ResponseEntity<BlogArticleBO> selectBlogArticleDetailById(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("article/inner/count")
    ResponseEntity<List<KeyValue<Long, Long>>> selectBlogArticleCountByCategoryIds(@RequestParam("categoryIds") Set<Long> categoryIds, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("article/inner/prevNext/{id}")
    ResponseEntity<List<BlogArticleBO>> selectBlogArticlePrevNext(@PathVariable("id") Long id, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("article/inner/tag/{tagId}")
    ResponseEntity<List<BlogArticleBO>> selectBlogArticleByTagId(@PathVariable("tagId") Long tagId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("article/inner/like/{articleId}")
    ResponseEntity<Integer> likeArticle(@PathVariable("articleId") Long articleId, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @GetMapping("article/inner/pv/{ids}")
    ResponseEntity<List<KeyValue<Long, Long>>> getPvByArticleId(@PathVariable("ids") Collection<Long> ids, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
