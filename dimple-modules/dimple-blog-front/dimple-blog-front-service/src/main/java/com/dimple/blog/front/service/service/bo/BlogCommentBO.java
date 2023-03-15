package com.dimple.blog.front.service.service.bo;

import com.dimple.common.core.annotation.Excel;
import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.List;


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
    @Excel(name = "文章标题")
    private String articleTitle;

    /**
     * login type, 0 local,1 GitHub
     */

    private Integer type;

    private Long visitorId;

    private String ip;
    private String browser;
    private String location;
    private String os;
    private Boolean admin;

    /**
     * user name
     */
    @Excel(name = "用户昵称")
    private String username;

    /**
     * parent comment is, default is -1
     */
    private Long parentId;

    /**
     * user head image
     */
    @Excel(name = "用户头像")
    private String avatars;

    /**
     * comment content, max length is 1024
     */
    @Excel(name = "内容")
    private String content;

    /**
     * user email, if email is not null will reply when the comment has been replied
     */
    @Excel(name = "邮件")
    private String email;

    private Long replyId;

    private String replyUsername;

    private Long likeCount;

    private String link;

    private List<BlogCommentBO> subComments;
}
