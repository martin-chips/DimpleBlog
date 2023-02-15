package com.dimple.blog.service.entity;

import lombok.Data;

/**
 * 对象 blog_article_category
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogArticleCategory {


    private Long id;


    private Long articleId;

    private Long categoryId;
}
