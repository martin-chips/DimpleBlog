package com.dimple.blog.service.mapper;

import com.dimple.blog.service.entity.BlogArticleCategory;

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

    /**
     * 新增
     *
     * @param blogArticleCategoryBO
     * @return 结果
     */
    int insertBlogArticleCategory(BlogArticleCategory blogArticleCategory);

    /**
     * 修改
     *
     * @param blogArticleCategoryBO
     * @return 结果
     */
    int updateBlogArticleCategory(BlogArticleCategory blogArticleCategory);

    /**
     * 删除
     *
     * @param id 主键
     * @return 结果
     */
    int deleteBlogArticleCategoryById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBlogArticleCategoryByIds(Long[] ids);
}
