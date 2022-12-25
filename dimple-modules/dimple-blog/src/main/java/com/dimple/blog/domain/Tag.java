package com.dimple.blog.domain;

import com.dimple.common.core.web.domain.BaseEntity;
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
