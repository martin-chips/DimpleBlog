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
    private Integer replyId;
    /**
     * 赞
     */
    private Integer good;
    /**
     * 踩
     */
    private Integer bad;
    /**
     * Ip地址
     */
    private String ip;
    /**
     * 操作系统类型
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 地址
     */
    private String location;
    /**
     * 是否审核通过，审核通过
     */
    private boolean display;
    /**
     * 审核记录
     */
    private String reviewMsg;

    /**
     * 当前页面的id
     */
    private Integer pageId;
    /**
     * 当前地址
     */
    private String url;

    private Comment parentComment;

}
