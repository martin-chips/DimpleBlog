package com.dimple.blog.front.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;


/**
 * 文章对象 blog_article
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogArticle extends BaseEntity {


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
    private String categoryTitle;

    /**
     * is original, true yes, false no
     */
    private Boolean original;

    /**
     * article summary,max length 512
     */
    private String summary;

    private Long likeCount;

    /**
     * article status, 1 is published, 2 is draft
     */
    private Integer articleStatus;
}
