package com.dimple.blog.front.web.controller.vo.params;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * BlogCommentVOParams
 *
 * @author Dimple
 * @date 2/14/2023 10:36 AM
 */
@Data
public class BlogCommentVOParams extends BaseVOParams {

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
