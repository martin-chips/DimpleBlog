package com.dimple.project.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Comment
 * @description:
 * @author: Dimple
 * @date: 10/24/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Comment extends BaseEntity {

    private Long id;
    /**
     * Email地址
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 地理位置
     */
    private String location;
    /**
     * 系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 父评论的id
     */
    private Long parentId;
    /**
     * QQ号码
     */
    private String qqNum;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 页面ID
     */
    private Long pageId;
    /**
     * 页面的URL
     */
    private String url;
    /**
     * 1表示显示,0表示不显示
     */
    private Boolean display;
    /**
     * 点赞
     */
    private Long good;
    /**
     * 踩
     */
    private Long bad;
    /**
     * 评论内容
     */
    private String content;
}
