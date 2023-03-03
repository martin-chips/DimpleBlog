package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogCategory;

import java.util.List;


/**
 * blog categoryMapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogCategoryMapper {
    /**
     * 查询blog category
     *
     * @param id blog category主键
     * @return blog category
     */
    BlogCategory selectBlogCategoryById(Long id);

    /**
     * 查询blog category列表
     *
     * @param blogCategory blog category
     * @return blog category集合
     */
    List<BlogCategory> selectBlogCategoryList(BlogCategory blogCategory);

}
