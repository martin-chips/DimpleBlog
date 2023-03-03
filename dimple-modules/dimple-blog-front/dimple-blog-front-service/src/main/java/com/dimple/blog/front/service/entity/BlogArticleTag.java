package com.dimple.blog.front.service.entity;

import lombok.Data;


/**
 * 对象 blog_article_tag
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}
