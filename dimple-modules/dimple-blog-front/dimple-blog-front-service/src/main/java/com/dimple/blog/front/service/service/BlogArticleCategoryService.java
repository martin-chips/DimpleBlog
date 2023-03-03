package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogArticleCategoryBO;

import java.util.List;


/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleCategoryService {
    /**
     * 查询
     *
     * @param id 主键
     * @return 
     */
    BlogArticleCategoryBO selectBlogArticleCategoryById(Long id);

    /**
     * 查询列表
     *
     * @param blogArticleCategoryBO 
     * @return 集合
     */
    List<BlogArticleCategoryBO> selectBlogArticleCategoryList(BlogArticleCategoryBO blogArticleCategoryBO);
}
