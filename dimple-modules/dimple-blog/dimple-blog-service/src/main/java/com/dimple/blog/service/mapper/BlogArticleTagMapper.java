package com.dimple.blog.service.mapper;

import com.dimple.blog.service.entity.BlogArticleTag;

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

    /**
     * 新增
     *
     * @param blogArticleTag
     * @return 结果
     */
    int insertBlogArticleTag(BlogArticleTag blogArticleTag);

    /**
     * 修改
     *
     * @param blogArticleTag
     * @return 结果
     */
    int updateBlogArticleTag(BlogArticleTag blogArticleTag);

    /**
     * 删除
     *
     * @param id 主键
     * @return 结果
     */
    int deleteBlogArticleTagById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBlogArticleTagByIds(List<Long> ids);

    BlogArticleTag selectBlogArticleTagByArticleId(Long articleId);
}
