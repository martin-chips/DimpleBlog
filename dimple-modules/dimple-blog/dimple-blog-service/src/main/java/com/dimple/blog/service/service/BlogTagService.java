package com.dimple.blog.service.service;

import com.dimple.blog.api.bo.BlogTagBO;

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

    List<BlogTagBO> selectBlogTagByTitles(List<String> titles);

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
     * @return the object id
     */
    Long insertBlogTag(BlogTagBO blogTag);

    /**
     * 修改
     *
     * @param blogTag
     * @return affected lines
     */
    int updateBlogTag(BlogTagBO blogTag);

    /**
     * 批量删除
     *
     * @param ids 需要删除的主键集合
     * @return affected lines
     */
    int deleteBlogTagByIds(Long[] ids);
}
