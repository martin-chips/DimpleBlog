package com.dimple.project.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @className: Blog
 * @description: 博客对象 bg_blog
 * @author: Dimple
 * @date: 2019-10-28
 */
@Data
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Blog extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 分类id
     */
    @NotNull(message = "分类Id不能为空", groups = {Publish.class})
    private Long categoryId;

    /**
     * 文章标题
     */
    @Length(min = 3, max = 50, message = "文章标题不能为空，且长度为{min}~{max}个字符", groups = {Publish.class, Draft.class})
    private String title;
    /**
     * 标签
     */
    @NotNull(message = "标签不能为空", groups = {Publish.class})
    private String tag;

    /**
     * 摘要
     */
    @NotNull(message = "摘要不能为空", groups = {Publish.class})
    @Length(min = 10, max = 150, message = "摘要长度为{min}~{max}个字符", groups = {Publish.class})
    private String summary;

    /**
     * 封面图片地址
     */
    @NotNull(message = "封面不能为空", groups = {Publish.class})
    @Length(max = 256, message = "封面地址不能超过256个字符", groups = {Publish.class})
    private String headerImg;

    /**
     * 正文内容
     */
    @NotNull(message = "正文内容不能为空", groups = {Publish.class, Draft.class})
    private String content;

    /**
     * 文章状态,1表示已经发表,0表示草稿箱
     */
    @NotNull(message = "状态设置不能为空", groups = {Publish.class, Draft.class})
    private Boolean status;

    /**
     * 是否允许评论,1表示允许,0表示不允许
     */
    @NotNull(message = "评论设置不能为空", groups = {Publish.class})
    private Boolean comment;
    /**
     * 推荐
     */
    @NotNull(message = "推荐设置不能为空", groups = {Publish.class})
    private Boolean support;
    /**
     * 权重
     */
    @Range(min = 1, max = 5, message = "权重长度为{min}~{max}个字符", groups = {Publish.class})
    private Long weight;
    /**
     * 分类
     */
    private Category category;

    private List<Comment> commentList;

    /**
     * 发布的校验规则
     */
    public interface Publish {
    }

    /**
     * 保存草稿的校验规则
     */
    public interface Draft {
    }
}
