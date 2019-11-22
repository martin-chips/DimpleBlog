package com.dimple.project.blog.mapper;

import com.dimple.project.blog.domain.BlogTag;

import java.util.List;

/**
 * @className: BlogTagMapper
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
public interface BlogTagMapper {

    List<BlogTag> selectBlogTagList(BlogTag blogTag);

    int deleteBlogTagByBlogId(Long blogId);

    int insertBlogTag(BlogTag blogTag);
}
