package com.dimple.blog.service.service;

import com.dimple.blog.service.service.bo.BlogTagBO;

import java.util.List;

/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogTagService {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogTagBO selectBlogTagById(Long id);

    List<BlogTagBO> selectBlogTagByIds(List<Long> ids);

    /**
     * 查询列表
     *
     * @param blogTag
     * @return 集合
     */
    List<BlogTagBO> selectBlogTagList(BlogTagBO blogTag);

    /**
     * 新增
     *
     * @param blogTag
     * @return 结果
     */
    Long insertBlogTag(BlogTagBO blogTag);

    /**
     * 修改
     *
     * @param blogTag
     * @return 结果
     */
    int updateBlogTag(BlogTagBO blogTag);

    /**
     * 批量删除
     *
     * @param ids 需要删除的主键集合
     * @return 结果
     */
    int deleteBlogTagByIds(Long[] ids);

    /**
     * 删除信息
     *
     * @param id 主键
     * @return 结果
     */
    int deleteBlogTagById(Long id);
}
