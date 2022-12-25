package com.dimple.blog.domain;

import com.dimple.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * Blog and category mapping
 *
 * @author Dimple
 */
@Data
public class ArticleCategory extends BaseEntity {

    private Integer id;

    private Integer articleId;

    private Integer categoryId;
}
