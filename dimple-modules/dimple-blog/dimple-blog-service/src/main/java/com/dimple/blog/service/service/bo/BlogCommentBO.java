package com.dimple.blog.service.service.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;


/**
 * 对象 blog_comment
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogCommentBO extends BaseEntity {
    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * comment article id
     */
    private Long articleId;

    private String articleTitle;

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
    private String headImage;

    /**
     * comment content, max length is 1024
     */
    private String content;

    /**
     * user email, if email is not null will reply when the comment has been replied
     */
    private String email;
}
