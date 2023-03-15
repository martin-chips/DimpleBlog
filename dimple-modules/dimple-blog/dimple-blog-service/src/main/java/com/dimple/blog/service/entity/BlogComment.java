package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;


/**
 * 对象 blog_comment
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogComment extends BaseEntity {

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

    private Long replyId;

    private Long likeCount;

    private String link;

    /**
     * user head image
     */
    private String avatars;
    private Long visitorId;
    private String os;
    private String browser;
    private String ip;
    private String location;

    /**
     * comment content, max length is 1024
     */
    private String content;

    /**
     * user email, if email is not null will reply when the comment has been replied
     */
    private String email;
}
