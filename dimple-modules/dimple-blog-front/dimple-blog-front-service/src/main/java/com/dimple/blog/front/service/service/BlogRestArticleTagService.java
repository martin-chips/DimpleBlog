package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.BlogArticleTagBO;

import java.util.List;


/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogRestArticleTagService {

    List<BlogArticleTagBO> selectBlogArticleTagByArticleId(Long article);
}
