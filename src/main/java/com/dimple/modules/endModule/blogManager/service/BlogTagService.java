package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.BlogTag;

/**
 * @className: BlogTagService
 * @description:
 * @auther: Owenb
 * @date: 01/28/19
 * @version: 1.0
 */
public interface BlogTagService {

    BlogTag insertBlogTag(int blogId, int tagId);

    /**
     * 删除博客与tag的关联
     *
     * @param blogId
     */
    void deleteByBlogId(Integer blogId);
}
