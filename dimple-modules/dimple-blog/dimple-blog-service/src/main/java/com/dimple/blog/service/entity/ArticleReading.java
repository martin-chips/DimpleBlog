package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * Blog reading count
 *
 * @author Dimple
 */
@Data
public class ArticleReading extends BaseEntity {
    private Integer id;

    private Integer blogId;

}
