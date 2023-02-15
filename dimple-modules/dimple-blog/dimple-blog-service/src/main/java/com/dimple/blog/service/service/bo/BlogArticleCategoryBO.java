package com.dimple.blog.service.service.bo;

import com.dimple.common.core.annotation.Excel;
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


    @Excel(name = "")
    private Long articleId;


    @Excel(name = "")
    private Long categoryId;
}
