package com.dimple.blog.service.mapper;


import com.dimple.blog.api.bo.KeyValue;
import com.dimple.blog.service.entity.BlogArticle;
import org.apache.ibatis.annotations.MapKey;

import java.util.Collection;
import java.util.List;
import java.util.Set;


/**
 * Blog articleMapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleMapper {
    /**
     * 查询文章
     *
     * @param id 文章主键
     * @return 文章
     */
    BlogArticle selectBlogArticleById(Long id);

    /**
     * 查询文章列表
     *
     * @param BlogArticle 文章
     * @return 文章集合
     */
    List<BlogArticle> selectBlogArticleList(BlogArticle BlogArticle);

    /**
     * 新增文章
     *
     * @param BlogArticle 文章
     * @return the object id
     */
    Long insertBlogArticle(BlogArticle BlogArticle);

    /**
     * 修改文章
     *
     * @param BlogArticle 文章
     * @return affected lines
     */
    int updateBlogArticle(BlogArticle BlogArticle);

    /**
     * 删除文章
     *
     * @param id 文章主键
     * @return affected lines
     */
    int deleteBlogArticleById(Long id);

    /**
     * 批量删除文章
     *
     * @param ids 需要删除的数据主键集合
     * @return affected lines
     */
    int deleteBlogArticleByIds(List<Long> ids);

    List<BlogArticle> selectBlogArticleByTagId(Long tagId);

    BlogArticle selectBlogArticleDetailById(Long id);

    List<BlogArticle> selectBlogArticleByIds(List<Long> ids);

    int likeArticle(Long id);

    List<KeyValue<Long, Long>> getPvByArticleId(Collection<Long> ids);

    List<BlogArticle> selectBlogArticlePrevNext(Long id);

    @MapKey("category_id")
    List<KeyValue<Long, Long>> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds);
}
