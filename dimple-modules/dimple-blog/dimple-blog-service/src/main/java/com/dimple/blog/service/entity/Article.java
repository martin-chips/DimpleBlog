package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * Blog table
 *
 * @author Dimple
 */
@Data
public class Article extends BaseEntity {
    /**
     * blog title
     */
    private String title;

    private String headerImage;

    private String content;

    private Integer categoryId;

    private Boolean original;

    private String summary;

    /**
     * blog status, 1 published, 2 draft
     */
    private Integer status;

}
