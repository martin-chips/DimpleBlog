package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogCategoryBO;

import java.util.List;


/**
 * blog categoryService接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogCategoryService {
    /**
     * 查询blog category
     *
     * @param id blog category主键
     * @return blog category
     */
    BlogCategoryBO selectBlogCategoryById(Long id);

    /**
     * 查询blog category列表
     *
     * @param blogCategory blog category
     * @return blog category集合
     */
    List<BlogCategoryBO> selectBlogCategoryList(BlogCategoryBO blogCategory);
}
