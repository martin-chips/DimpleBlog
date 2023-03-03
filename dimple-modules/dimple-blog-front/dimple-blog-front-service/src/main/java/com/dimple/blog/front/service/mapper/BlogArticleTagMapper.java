package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogArticleTag;

import java.util.List;


/**
 * Mapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleTagMapper {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogArticleTag selectBlogArticleTagById(Long id);

    /**
     * 查询列表
     *
     * @param blogArticleTag
     * @return 集合
     */
    List<BlogArticleTag> selectBlogArticleTagList(BlogArticleTag blogArticleTag);


    List<BlogArticleTag> selectBlogArticleTagByArticleId(Long articleId);
}
