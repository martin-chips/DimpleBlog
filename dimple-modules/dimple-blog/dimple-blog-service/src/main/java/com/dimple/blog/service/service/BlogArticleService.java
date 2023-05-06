package com.dimple.blog.service.service;


import com.dimple.blog.api.bo.BlogArticleBO;
import com.dimple.blog.api.bo.KeyValue;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Blog articleService接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleService {
    /**
     * 查询文章. not contain content
     *
     * @param id 文章主键
     * @return 文章
     */
    BlogArticleBO selectBlogArticleById(Long id);


    List<BlogArticleBO> selectBlogArticleByIds(List<Long> ids);

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

    /**
     * 新增文章
     *
     * @param blogArticle 文章
     * @return the object id
     */
    Long insertBlogArticle(BlogArticleBO blogArticle);

    /**
     * 修改文章
     *
     * @param blogArticle 文章
     * @return affected lines
     */
    int updateBlogArticle(BlogArticleBO blogArticle);

    /**
     * 批量删除文章
     *
     * @param ids 需要删除的文章主键集合
     * @return affected lines
     */
    int deleteBlogArticleByIds(List<Long> ids);

    /**
     * 删除文章信息
     *
     * @param id 文章主键
     * @return affected lines
     */
    int deleteBlogArticleById(Long id);

    /**
     * update article status
     *
     * @param id            article id
     * @param articleStatus article status,{@link com.dimple.blog.service.enums.BlogArticleStatus}
     * @return affected lines
     */
    int updateBlogArticleStatus(Long id, Integer articleStatus);

    List<BlogArticleBO> selectBlogArticlePrevNext(Long id);

    List<BlogArticleBO> selectBlogArticleByTagId(Long tagId);

    int likeArticle(Long articleId);

    List<KeyValue<Long, Long>> getPvByArticleId(Collection<Long> ids);

    List<KeyValue<Long, Long>> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds);
}
