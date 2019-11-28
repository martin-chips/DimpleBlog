package com.dimple.project.book.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * @className: Book
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
public class Book implements Serializable {
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
    private Date publishTime;
    /**
    * 页数
    */
    private Integer pageNum;
    /**
    * 评分
    */
    private Double grade;
    /**
    * 简介
    */
    private String description;
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
    private Object status;
    /**
    * 读书状态
    */
    private Integer progress;
    /**
    * 是否推荐
    */
    private Object support;
    
    private Date createTime;
    
    private String createBy;
    
    private String updateBy;
    
    private Date updateTime;
    
    private String deleteBy;
    
    private Date deleteTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Object getSupport() {
        return support;
    }

    public void setSupport(Object support) {
        this.support = support;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}