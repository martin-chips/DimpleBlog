package com.dimple.blog.front.web.controller.vo.params;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private Long articleId;

    /**
     * user name
     */
    @NotEmpty
    private String username;
    /**
     * login type, 0 local,1 GitHub
     */

    private Integer type;

    private Long visitorId;

    /**
     * parent comment is, default is -1
     */
    private Long parentId;

    private Long replyId;

    /**
     * user head image
     */
    @NotNull
    private String avatars;

    private String link;

    /**
     * comment content, max length is 1024
     */
    @NotNull
    @Length(max = 1000)
    private String content;

    /**
     * user email, if email is not null will reply when the comment has been replied
     */
    @Email
    private String email;
}
