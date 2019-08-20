package com.dimple.project.blog.comment.domain;

import com.dimple.framework.aspectj.lang.annotation.Excel;
import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

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

    @Excel(name = "QQ昵称")
    private String nickname;

    @Excel(name = "QQ号码")
    private Long qqNum;

    @Excel(name = "邮箱地址")
    private String email;

    @Excel(name = "QQ头像地址")
    private String avatar;

    @Excel(name = "留言")
    private String content;

    @Excel(name = "回复的上一个留言的id")
    private Integer replyId;

    @Excel(name = "收到评论是否邮件通知")
    private boolean reply;

    @Excel(name = "评论的页面地址")
    private String url;

    @Excel(name = "页面id")
    private Integer pageId;

    @Excel(name = "点赞数")
    private Integer good;

    @Excel(name = "踩数")
    private Integer bad;

    @Excel(name = "IP 地址")
    private String ip;

    @Excel(name = "地理位置")
    private String location;

    @Excel(name = "操作系统")
    private String os;

    @Excel(name = "浏览器类型")
    private String browser;

    @Excel(name = "是否显示", readConverterExp = "=核通过显示，审核不通过不显示")
    private boolean display;

    @Excel(name = "审核信息")
    private String reviewMsg;

    @Excel(name = "审核信息", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDatetime;

    private Comment parentComment;

}
