package com.dimple.blog.service.service.bo;

import com.dimple.common.core.annotation.Excel;
import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.List;


/**
 * Blog article对象 blog_article
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogArticleBO extends BaseEntity {


    private Long id;

    /**
     * article title
     */
    @Excel(name = "article title")
    private String title;

    /**
     * article header image
     */
    @Excel(name = "article header image")
    private String headerImage;

    /**
     * article content
     */
    @Excel(name = "article content")
    private String content;

    /**
     * article category id
     */
    @Excel(name = "article category id")
    private Long categoryId;

    /**
     * is original, 1 yes, 0 false
     */
    @Excel(name = "is original, 1 yes, 0 false")
    private Integer original;

    /**
     * article summary,max length 512
     */
    @Excel(name = "article summary,max length 512")
    private String summary;

    /**
     * article status, 1 is published, 2 is draft
     */
    @Excel(name = "article status, 1 is published, 2 is draft")
    private Integer articleStatus;

    private List<BlogTagBO> blogTags;

}
