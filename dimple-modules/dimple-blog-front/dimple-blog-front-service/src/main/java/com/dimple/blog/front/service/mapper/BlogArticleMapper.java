package com.dimple.blog.front.service.mapper;


import com.dimple.blog.front.service.entity.BlogArticle;
import com.dimple.blog.front.service.entity.KeyValue;
import org.apache.ibatis.annotations.MapKey;

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


    BlogArticle selectBlogArticleDetailById(Long id);

    List<BlogArticle> selectBlogArticleByIds(List<Long> ids);

    @MapKey("category_id")
    List<KeyValue<Long,Long>> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds);

}
