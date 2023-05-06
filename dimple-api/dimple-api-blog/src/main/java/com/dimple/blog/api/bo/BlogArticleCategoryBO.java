package com.dimple.blog.api.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * 对象 blog_article_category
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogArticleCategoryBO extends BaseEntity {


    private Long id;


    private Long articleId;


    private Long categoryId;
}
