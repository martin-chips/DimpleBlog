package com.dimple.blog.front.service.entity;

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

    /**
     * login type, 0 local,1 GitHub
     */

    private Integer type;

    private String visitorId;

    private String ip;
    private String location;
    private String os;

    private String browser;

    private Boolean admin;

    private String link;

    /**
     * user head image
     */
    private String avatars;

    /**
     * comment content, max length is 1024
     */
    private String content;

    /**
     * user email, if email is not null will reply when the comment has been replied
     */
    private String email;
}
