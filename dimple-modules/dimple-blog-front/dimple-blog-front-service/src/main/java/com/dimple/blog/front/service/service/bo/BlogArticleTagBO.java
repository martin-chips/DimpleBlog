package com.dimple.blog.front.service.service.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;


/**
 * 对象 blog_article_tag
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogArticleTagBO extends BaseEntity {

    private Long id;

    private Long articleId;

    private Long tagId;
}
