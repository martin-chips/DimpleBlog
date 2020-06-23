package com.dimple.domain;

import com.dimple.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @className: Blog
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Entity
@Table(name = "bg_blog")
public class Blog extends BaseEntity implements Serializable {

    @Id
    @Column(name = "blog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Update.class})
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

//    private Long categoryId;

    @ApiModelProperty("文章标题")
    @Length(min = 3, max = 100, message = "文章标题不能为空，且长度为{min}~{max}个字符", groups = {Create.class, Update.class})
    private String title;

    @ApiModelProperty("摘要")
    @NotNull(message = "摘要不能为空", groups = {Create.class})
    @Length(min = 10, max = 250, message = "摘要长度为{min}~{max}个字符", groups = {Create.class})
    private String summary;

    @ApiModelProperty("封面图片地址")
    @NotNull(message = "封面不能为空", groups = {Create.class})
    @Length(max = 256, message = "封面地址不能超过256个字符", groups = {Create.class})
    private String headerImg;

    @ApiModelProperty("封面类型(1表示普通,0表示没有,2表示大图)")
    private Integer headerImgType;

    @ApiModelProperty("正文内容")
    @NotNull(message = "正文内容不能为空", groups = {Create.class, Update.class})
    @Basic(fetch = FetchType.LAZY)
    private String content;

    @ApiModelProperty("HTML 格式化后的内容")
    @Basic(fetch = FetchType.LAZY)
    @NotNull(message = "正文内容不能为空", groups = {Create.class, Update.class})
    private String htmlContent;

    @ApiModelProperty("文章状态,1表示已经发表,0表示草稿箱")
    @NotNull(message = "状态设置不能为空", groups = {Create.class, Update.class})
    private Boolean status;

    @ApiModelProperty("是否允许评论,1表示允许,0表示不允许")
    @NotNull(message = "评论设置不能为空", groups = {Create.class})
    private Boolean comment;

    @ApiModelProperty("是否允许推荐,1表示推荐,0表示不推荐")
    @NotNull(message = "推荐设置不能为空", groups = {Create.class})
    private Boolean support;

    @ApiModelProperty("权重")
    @Range(min = 1, max = 5, message = "权重长度为{min}~{max}个字符", groups = {Create.class})
    private Long weight;

    @ApiModelProperty("点赞数")
    private Long like;

    @ApiModelProperty("点击数")
    private Long click;

    @ApiModelProperty("分类")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    @ApiModelProperty("标签集合")
//    private List<Tag> tagList;
//
//    @ApiModelProperty("标签名集合")
//    private List<String> tagTitleList;
//
//    @ApiModelProperty("评论集合")
//    private List<Comment> commentList;
//
//    @ApiModelProperty("评论数量")
//    private Long commentCount;

}
