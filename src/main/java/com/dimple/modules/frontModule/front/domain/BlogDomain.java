package com.dimple.modules.frontModule.front.domain;

import lombok.Data;

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
@Data
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
}
