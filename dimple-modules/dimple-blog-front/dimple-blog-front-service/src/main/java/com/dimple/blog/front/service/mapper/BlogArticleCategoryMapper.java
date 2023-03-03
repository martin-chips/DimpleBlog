package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogArticleCategory;

import java.util.List;


/**
 * Mapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleCategoryMapper {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogArticleCategory selectBlogArticleCategoryById(Long id);

    /**
     * 查询列表
     *
     * @param blogArticleCategory
     * @return 集合
     */
    List<BlogArticleCategory> selectBlogArticleCategoryList(BlogArticleCategory blogArticleCategory);

}
