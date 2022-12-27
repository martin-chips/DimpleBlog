package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * blog category
 *
 * @author Dimple
 */
@Data
public class Category extends BaseEntity {
    private Integer id;
    private String title;
}
