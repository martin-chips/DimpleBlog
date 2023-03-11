package com.dimple.blog.web.controller.vo.params;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

import java.util.List;

/**
 * Blog table
 *
 * @author Dimple
 */
@Data
public class BlogArticleVOParams extends BaseVOParams {

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

    private List<String> tags;

}
