package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * blog tag
 *
 * @author Dimple
 */
@Data
public class Tag extends BaseEntity {
    private Integer id;

    private String title;
}
