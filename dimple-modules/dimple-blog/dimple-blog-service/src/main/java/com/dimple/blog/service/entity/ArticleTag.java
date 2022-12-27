package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * Blog and tag mapping
 *
 * @author Dimple
 */
@Data
public class ArticleTag extends BaseEntity {
    private Integer id;

    private Integer articleId;

    private Integer tagId;
}
