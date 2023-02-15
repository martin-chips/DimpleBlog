package com.dimple.blog.service.service;


import com.dimple.blog.service.service.bo.BlogArticleBO;

import java.util.List;

/**
 * Blog articleService接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogArticleService {
    /**
     * 查询Blog article. not contain content
     *
     * @param id Blog article主键
     * @return Blog article
     */
    BlogArticleBO selectBlogArticleById(Long id);


    List<BlogArticleBO> selectBlogArticleByIds(List<Long> ids);

    /**
     * detail contain content
     *
     * @param id the article id
     * @return all field will be filled.
     */
    BlogArticleBO selectBlogArticleDetailById(Long id);

    /**
     * 查询Blog article列表
     *
     * @param blogArticle Blog article
     * @return Blog article集合
     */
    List<BlogArticleBO> selectBlogArticleList(BlogArticleBO blogArticle);

    /**
     * 新增Blog article
     *
     * @param blogArticle Blog article
     * @return 结果
     */
    Long insertBlogArticle(BlogArticleBO blogArticle);

    /**
     * 修改Blog article
     *
     * @param blogArticle Blog article
     * @return 结果
     */
    int updateBlogArticle(BlogArticleBO blogArticle);

    /**
     * 批量删除Blog article
     *
     * @param ids 需要删除的Blog article主键集合
     * @return 结果
     */
    int deleteBlogArticleByIds(List<Long> ids);

    /**
     * 删除Blog article信息
     *
     * @param id Blog article主键
     * @return 结果
     */
    int deleteBlogArticleById(Long id);
}
