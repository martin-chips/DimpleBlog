package com.dimple.blog.service.entity;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * Blog and tag mapping
 *
 * @author Dimple
 */
@Data
public class ArticleTag extends BaseVOParams {
    private Integer id;

    private Integer articleId;

    private Integer tagId;
}
