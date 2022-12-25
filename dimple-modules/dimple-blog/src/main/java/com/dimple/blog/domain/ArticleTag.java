package com.dimple.blog.domain;

import com.dimple.common.core.web.domain.BaseEntity;
import lombok.Data;

import java.util.Base64;

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
