package com.dimple.blog.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * BlogCategoryVOParams
 *
 * @author Dimple
 */
@Data
public class BlogCategoryVO extends BaseEntity {
    private Long id;
    private String title;

}
