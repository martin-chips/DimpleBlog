package com.dimple.project.blog.domain;

import lombok.Data;

/**
 * @className: BlogTag
 * @description: Blog和Tag的关联表
 * @author: Dimple
 * @date: 11/22/19
 */
@Data
public class BlogTag {
    private Long id;
    private Long blogId;
    private Long tagId;

    public BlogTag(Long blogId, Long tagId) {
        this.blogId = blogId;
        this.tagId = tagId;
    }
}
