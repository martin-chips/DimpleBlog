package com.dimple.blog.front.service.service.bo;

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
    @Excel(name = "标题")
    private String title;

    /**
     * article header image
     */
    @Excel(name = "头图")
    private String headerImage;

    /**
     * article content
     */
    @Excel(name = "内容")
    private String content;

    /**
     * article category id
     */
    private Long categoryId;

    private String categoryTitle;

    /**
     * is original, true yes, false no
     */
    @Excel(name = "原创")
    private Boolean original;

    /**
     * article summary,max length 512
     */
    @Excel(name = "摘要")
    private String summary;

    /**
     * article status, 1 is published, 2 is draft
     */
    @Excel(name = "状态")
    private Integer articleStatus;
    @Excel(name = "点赞数量")
    private Long likeCount;

    private Long commentCount;

    private Long pv;
    private List<String> blogTags;

}
