package com.dimple.blog.api;

import com.dimple.blog.api.bo.BlogCategoryBO;
import com.dimple.blog.api.factory.RemoteBlogCategoryServiceFallbackFactory;
import com.dimple.blog.api.model.BlogCategoryDTO;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.constant.ServiceNameConstants;
import com.dimple.common.core.domain.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * RemoteBlogCategoryService
 *
 * @author Dimple
 * @date 3/31/2023
 */
@FeignClient(contextId = "remoteBlogCategoryService", value = ServiceNameConstants.BLOG_SERVICE, fallbackFactory = RemoteBlogCategoryServiceFallbackFactory.class)
public interface RemoteBlogCategoryService {
    @PostMapping("category/inner/list")
    ResponseEntity<List<BlogCategoryBO>> selectBlogCategoryList(@RequestBody BlogCategoryDTO blogCategoryDTO, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
