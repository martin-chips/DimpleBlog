package com.dimple.project.blog.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.dimple.project.blog.category.domain.Category;
import com.dimple.project.blog.tag.domain.Tag;
import lombok.Data;

import java.util.List;

/**
 * @className: Blog
 * @description: 博客信息
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.1
 */
@Data
public class Blog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id
     */
    private Integer blogId;

    /**
     * 归类id
     */
    private Integer categoryId;

    /**
     * 博文标题
     */
    private String title;

    /**
     * 博文摘要
     */
    private String summary;

    /**
     * 博文状态，1表示已经发表，2表示在草稿箱，3表示在垃圾箱
     */
    private String status;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 是否推荐，Y表示推荐，N表示不推荐
     */
    private String support;

    /**
     * 点击次数
     */
    private Integer click;

    /**
     * 标图展示地址
     */
    private String headerImg;

    /**
     * 博文类型，1表示markdown，2表示富文本
     */
    private String type;

    /**
     * 博文正文内容
     */
    private String content;
    /**
     * tag集合
     */
    private List<Tag> tagList;

    /**
     * 从页面获取或者传到页面的tags
     */
    private String[] tags;
    /**
     * 分类的名称
     */
    private String categoryTitle;

    private Category category;

    /**
     * 评论数量
     */
    private Integer commentCount;
    /**
     * 是否允许评论
     */
    private boolean allowComment;
}