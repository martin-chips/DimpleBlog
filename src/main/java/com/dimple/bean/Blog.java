package com.dimple.bean;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable {
    /**
     * 博客的主键
     */
    private Integer blogId;

    /**
     * 博客所属分类
     */
    private Integer categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱
     */
    private Integer status;

    /**
     * 是否推荐
     */
    private Boolean support;

    /**
     * 标签
     */
    private String tags;

    /**
     * 点击次数
     */
    private Integer click;

    /**
     * 置顶量
     */
    private Integer weight;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 头像地址
     */
    private String headerUrl;

    /**
     * 内容
     */
    private String content;

    /**
     * blog
     */
    private static final long serialVersionUID = 1L;

    /**
     * 博客的主键
     * @return blog_id 博客的主键
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 博客的主键
     * @param blogId 博客的主键
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 博客所属分类
     * @return category_id 博客所属分类
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 博客所属分类
     * @param categoryId 博客所属分类
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 摘要
     * @return summary 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 摘要
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱
     * @return status 状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱
     * @param status 状态，1表示已发表，2表示在草稿箱，3表示在垃圾箱
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否推荐
     * @return support 是否推荐
     */
    public Boolean getSupport() {
        return support;
    }

    /**
     * 是否推荐
     * @param support 是否推荐
     */
    public void setSupport(Boolean support) {
        this.support = support;
    }

    /**
     * 标签
     * @return tags 标签
     */
    public String getTags() {
        return tags;
    }

    /**
     * 标签
     * @param tags 标签
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 点击次数
     * @return click 点击次数
     */
    public Integer getClick() {
        return click;
    }

    /**
     * 点击次数
     * @param click 点击次数
     */
    public void setClick(Integer click) {
        this.click = click;
    }

    /**
     * 置顶量
     * @return weight 置顶量
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 置顶量
     * @param weight 置顶量
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 头像地址
     * @return header_url 头像地址
     */
    public String getHeaderUrl() {
        return headerUrl;
    }

    /**
     * 头像地址
     * @param headerUrl 头像地址
     */
    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}