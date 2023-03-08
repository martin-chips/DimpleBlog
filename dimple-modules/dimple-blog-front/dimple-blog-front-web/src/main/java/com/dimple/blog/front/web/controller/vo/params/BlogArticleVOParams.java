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
     * article category id
     */
    private Long categoryId;


    /**
     * article summary,max length 512
     */
    private String summary;

    private Long tagId;
}
