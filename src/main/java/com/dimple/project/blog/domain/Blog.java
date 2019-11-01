package com.dimple.project.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Blog
 * @description: 博客对象 bg_blog
 * @author: Dimple
 * @date: 2019-10-28
 */
@Data
@NoArgsConstructor
public class Blog extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 文章标题
     */
    private String title;
    /**
     * 标签
     */
    private String tag;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 封面图片地址
     */
    private String headerImg;

    /**
     * 正文内容
     */
    private String content;

    /**
     * 文章状态,1表示已经发表,0表示草稿箱
     */
    private Boolean status;

    /**
     * 是否允许评论,1表示允许,0表示不允许
     */
    private Boolean comment;
    /**
     * 推荐
     */
    private Boolean support;
    /**
     * 权重
     */
    private Long weight;
}
