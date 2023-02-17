package com.dimple.blog.service.mapper;

import com.dimple.blog.service.entity.BlogTag;

import java.util.List;


/**
 * Mapper接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogTagMapper {
    /**
     * 查询
     *
     * @param id 主键
     * @return
     */
    BlogTag selectBlogTagById(Long id);

    /**
     * 查询列表
     *
     * @param blogTag
     * @return 集合
     */
    List<BlogTag> selectBlogTagList(BlogTag blogTag);

    /**
     * 新增
     *
     * @param blogTag
     * @return the object id
     */
    Long insertBlogTag(BlogTag blogTag);

    /**
     * 修改
     *
     * @param blogTag
     * @return affected lines
     */
    int updateBlogTag(BlogTag blogTag);

    /**
     * 删除
     *
     * @param id 主键
     * @return affected lines
     */
    int deleteBlogTagById(Long id);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据主键集合
     * @return affected lines
     */
    int deleteBlogTagByIds(Long[] ids);

    List<BlogTag> selectBlogTagListByIds(List<Long> ids);
}
