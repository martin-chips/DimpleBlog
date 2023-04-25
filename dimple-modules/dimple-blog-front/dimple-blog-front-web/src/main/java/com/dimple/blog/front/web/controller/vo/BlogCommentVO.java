package com.dimple.blog.front.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * BlogCommentVO
 *
 * @author Dimple
 * @date 2/14/2023 10:36 AM
 */
@Data
public class BlogCommentVO extends BaseEntity {
    private Long id;

    /**
     * comment article id
     */
    private Long articleId;

    /**
     * user name
     */
    private String username;

    /**
     * parent comment is, default is -1
     */
    private Long parentId;

    /**
     * user head image
     */
    private String avatars;

    private Integer type;

    private String visitorId;

    private String location;

    private Boolean admin;

    /**
     * comment content, max length is 1024
     */
    private String content;

    /**
     * user email, if email is not null will reply when the comment has been replied
     */
    private String email;

    private Long replyId;

    private String link;

    private String replyUsername;

    private Long likeCount;

    private List<BlogCommentVO> subComments;
}
