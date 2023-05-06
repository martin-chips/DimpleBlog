package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.BlogCategoryBO;

import java.util.List;


/**
 * blog categoryService接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogRestCategoryService {

    /**
     * 查询blog category列表
     *
     * @param blogCategory blog category
     * @return blog category集合
     */
    List<BlogCategoryBO> selectBlogCategoryList(BlogCategoryBO blogCategory);
}
