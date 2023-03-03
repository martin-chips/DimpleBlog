package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogArticleTagBO;

import java.util.List;


/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleTagService {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogArticleTagBO selectBlogArticleTagById(Long id);

    List<BlogArticleTagBO> selectBlogArticleTagByArticleId(Long article);

    /**
     * 查询列表
     *
     * @param blogArticleTag
     * @return 集合
     */
    List<BlogArticleTagBO> selectBlogArticleTagList(BlogArticleTagBO blogArticleTag);



}
