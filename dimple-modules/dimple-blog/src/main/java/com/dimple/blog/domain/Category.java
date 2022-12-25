package com.dimple.blog.domain;

import com.dimple.common.core.web.domain.BaseEntity;
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
