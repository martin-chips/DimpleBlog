package com.dimple.blog.service.mapper;

import com.dimple.blog.service.entity.BlogCategory;

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

    /**
     * 新增blog category
     *
     * @param blogCategory blog category
     * @return affected lines
     */
    int insertBlogCategory(BlogCategory blogCategory);

    /**
     * 修改blog category
     *
     * @param blogCategory blog category
     * @return affected lines
     */
    int updateBlogCategory(BlogCategory blogCategory);

    /**
     * 删除blog category
     *
     * @param id blog category主键
     * @return affected lines
     */
    int deleteBlogCategoryById(Long id);

    /**
     * 批量删除blog category
     *
     * @param ids 需要删除的数据主键集合
     * @return affected lines
     */
    int deleteBlogCategoryByIds(Long[] ids);
}
