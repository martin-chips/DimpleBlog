package com.dimple.blog.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * BlogTagVO
 *
 * @author Dimple
 * @date 2/14/2023 10:23 AM
 */
@Data
public class BlogTagVO extends BaseEntity {
    private Long id;

    /**
     * the article tag
     */
    private String title;
}
