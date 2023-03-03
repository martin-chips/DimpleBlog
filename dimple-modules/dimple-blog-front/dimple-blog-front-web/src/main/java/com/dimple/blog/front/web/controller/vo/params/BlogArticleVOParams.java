package com.dimple.blog.front.web.controller.vo.params;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * Blog table
 *
 * @author Dimple
 */
@Data
public class BlogArticleVOParams extends BaseEntity {

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
     * is original, true yes, false no
     */
    private Boolean original;

    /**
     * article summary,max length 512
     */
    private String summary;

    /**
     * article status, 1 is published, 2 is draft
     */
    private Integer articleStatus;

}
