package com.dimple.project.blog.comment.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: Comment
 * @description: 留言
 * @auther: Dimple
 * @date: 2019/7/17
 * @version: 1.0
 */
@Data
public class Comment extends BaseEntity {
    private Integer id;
    /**
     * QQ 昵称
     */
    private String nickname;
    /**
     * QQ号
     */
    private Long qqNum;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 页面Id
     */
    private Integer pageId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 当有回复的是否邮件通知
     */
    private boolean reply;
    /**
     * 上级评论的id
     */
    private Integer parentId;

    private Comment parentComment;

}
