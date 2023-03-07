package com.dimple.blog.front.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private List<String> blogTags;

    private Long pv;

    private Long commentCount;

    private Long likeCount;

}
