package com.dimple.blog.service.mapper;


import com.dimple.blog.service.entity.BlogArticle;

import java.util.List;


/**
 * Blog articleMapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleMapper {
    /**
     * 查询Blog article
     *
     * @param id Blog article主键
     * @return Blog article
     */
    BlogArticle selectBlogArticleById(Long id);

    /**
     * 查询Blog article列表
     *
     * @param BlogArticle Blog article
     * @return Blog article集合
     */
    List<BlogArticle> selectBlogArticleList(BlogArticle BlogArticle);

    /**
     * 新增Blog article
     *
     * @param BlogArticle Blog article
     * @return 结果
     */
    Long insertBlogArticle(BlogArticle BlogArticle);

    /**
     * 修改Blog article
     *
     * @param BlogArticle Blog article
     * @return 结果
     */
    int updateBlogArticle(BlogArticle BlogArticle);

    /**
     * 删除Blog article
     *
     * @param id Blog article主键
     * @return 结果
     */
    int deleteBlogArticleById(Long id);

    /**
     * 批量删除Blog article
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteBlogArticleByIds(List<Long> ids);

    BlogArticle selectBlogArticleDetailById(Long id);

    List<BlogArticle> selectBlogArticleByIds(List<Long> ids);
}
