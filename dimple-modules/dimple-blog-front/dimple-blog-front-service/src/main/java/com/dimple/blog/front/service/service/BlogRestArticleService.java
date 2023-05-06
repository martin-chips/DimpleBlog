package com.dimple.blog.front.service.service;


import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.api.bo.BlogArticlePrevNextBO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Blog articleService接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogRestArticleService {
    /**
     * 查询文章. not contain content
     *
     * @param id 文章主键
     * @return 文章
     */
    BlogArticleBO selectBlogArticleById(Long id);

    /**
     * detail contain content
     *
     * @param id the article id
     * @return all field will be filled.
     */
    BlogArticleBO selectBlogArticleDetailById(Long id);

    /**
     * 查询文章列表
     *
     * @param blogArticle 文章
     * @return 文章集合
     */
    List<BlogArticleBO> selectBlogArticleList(BlogArticleBO blogArticle);

    Map<Long, Long> selectBlogArticleCountByCategoryIds(Set<Long> collect);

    BlogArticlePrevNextBO selectPrevNextArticle(Long id);

    List<BlogArticleBO> selectBlogArticleListByTagId(Long tagId);

    int likeArticle(Long id);
}
