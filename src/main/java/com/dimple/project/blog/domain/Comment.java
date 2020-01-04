package com.dimple.project.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @className: Comment
 * @description: 因为只设置为二级回复, 那么超过二级的回复会被直接并入二级
 * parentId为null的为第一级回复,那么接下来的所有parentId不为null的为第二级回复.
 * @author: Dimple
 * @date: 10/24/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Comment extends BaseEntity implements Serializable {

    private Long id;
    /**
     * Email地址
     */
    @Email(message = "Email地址不合法")
    private String email;
    /**
     * 昵称
     */
    @NotNull(message = "昵称不能为空")
    @Length(min = 1, max = 100, message = "昵称长度为{min}~{max}个字符")
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
     * 回复的id
     */
    private Long replyId;
    /**
     * 接收回复邮件
     */
    private Boolean reply;
    /**
     * QQ号码
     */
    @Length(max = 20, message = "QQ号码长度不能超过{max}")
    private String qqNum;
    /**
     * 头像地址
     */
    @Length(max = 256, message = "头像地址长度不能超过{max}")
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
    @NotNull(message = "内容不能为空")
    private String content;
    /**
     * Html评论内容
     */
    @NotNull(message = "内容不能为空")
    private String htmlContent;
    /**
     * true代表为站长回复,false代表不是
     */
    private Boolean adminReply;

    private Comment parentComment;
    /**
     * 子评论
     */
    private List<Comment> subCommentList;
    /**
     * 回复的NickName
     */
    private String replyNickName;
}
