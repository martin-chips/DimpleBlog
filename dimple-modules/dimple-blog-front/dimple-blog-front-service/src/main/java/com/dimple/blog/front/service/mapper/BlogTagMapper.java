package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogTag;

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


    List<BlogTag> selectBlogTagListByIds(List<Long> ids);
}
