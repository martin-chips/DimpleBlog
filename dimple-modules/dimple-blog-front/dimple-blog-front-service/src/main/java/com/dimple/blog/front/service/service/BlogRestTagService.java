package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.BlogTagBO;

import java.util.List;

/**
 * Service接口
 *
 * @author Dimple
 * @date 2023-02-13
 */
public interface BlogRestTagService {

    List<BlogTagBO> selectBlogTagByIds(List<Long> ids);

    /**
     * 查询列表
     *
     * @param blogTag
     * @return 集合
     */
    List<BlogTagBO> selectBlogTagList(BlogTagBO blogTag);

}
