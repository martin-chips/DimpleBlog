package com.dimple.blog.service.service;

import com.dimple.blog.api.bo.BlogCategoryBO;

import java.util.Collection;
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
    List<BlogCategoryBO> selectBlogCategoryByIds(Collection<Long> ids);

    /**
     * 查询blog category列表
     *
     * @param blogCategory blog category
     * @return blog category集合
     */
    List<BlogCategoryBO> selectBlogCategoryList(BlogCategoryBO blogCategory);

    /**
     * 新增blog category
     *
     * @param blogCategory blog category
     * @return affected lines
     */
    int insertBlogCategory(BlogCategoryBO blogCategory);

    /**
     * 修改blog category
     *
     * @param blogCategory blog category
     * @return affected lines
     */
    int updateBlogCategory(BlogCategoryBO blogCategory);

    /**
     * 批量删除blog category
     *
     * @param ids 需要删除的blog category主键集合
     * @return affected lines
     */
    int deleteBlogCategoryByIds(Long[] ids);

    /**
     * 删除blog category信息
     *
     * @param id blog category主键
     * @return affected lines
     */
    int deleteBlogCategoryById(Long id);
}
