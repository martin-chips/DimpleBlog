package com.dimple.modules.frontModule.front.domain;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @className: BlogDomain
 * @description: 用于前端展示的Blog实体类
 * @auther: Owenb
 * @date: 01/25/19
 * @version: 1.0
 */

public class BlogDomain {
    private Integer blogId;
    private Integer categoryId;
    private String categoryTitle;
    private String title;
    private String summary;
    @Temporal(TemporalType.DATE)
    private Date createTime;
    private String tags;
    private Integer click;
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    private String headerUrl;

    public BlogDomain() {

    }

    public BlogDomain(Integer blogId, Integer categoryId, String categoryTitle, String title, String summary, Date createTime, String tags, Integer click, Date updateTime, String headerUrl) {
        this.blogId = blogId;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.title = title;
        this.summary = summary;
        this.createTime = createTime;
        this.tags = tags;
        this.click = click;
        this.updateTime = updateTime;
        this.headerUrl = headerUrl;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }
}
