package com.dimple.project.book.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @className: Book
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
@Data
public class Book extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 210329541652033547L;

    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 封面
     */
    private String headerImg;
    /**
     * 作者
     */
    private String author;
    /**
     * 分类Id
     */
    private Long categoryId;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 出版时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishTime;
    /**
     * 页数
     */
    private Integer pageNum;
    /**
     * 评分
     */
    private BigDecimal grade;
    /**
     * 简介
     */
    private String summary;
    /**
     * 原书目录
     */
    private String catalog;
    /**
     * 阅读量
     */
    private Long click;

    private Long like;
    /**
     * true表示发布,false表示草稿箱
     */
    private Boolean status;
    /**
     * 读书状态
     */
    private Integer progress;
    /**
     * 是否推荐
     */
    private Boolean support;
}
