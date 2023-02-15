package com.dimple.blog.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * Blog table
 *
 * @author Dimple
 */
@Data
public class BlogArticleVO extends BaseEntity {
    private Long id;

    /**
     * article title
     */
    private String title;

    /**
     * article header image
     */
    private String headerImage;

    /**
     * article content
     */
    private String content;

    /**
     * article category id
     */
    private Long categoryId;

    /**
     * is original, 1 yes, 0 false
     */
    private Integer original;

    /**
     * article summary,max length 512
     */
    private String summary;

    /**
     * article status, 1 is published, 2 is draft
     */
    private Integer articleStatus;
}
