package com.dimple.bean;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    /**
     * 分类的id
     */
    private Integer categoryId;

    /**
     * 分类的名称
     */
    private String title;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 分类是否推荐（前台首页界面展示）
     */
    private Integer weight;

    /**
     * 描述
     */
    private String description;

    /**
     * 首页推荐
     */
    private Boolean support;

    /**
     * category
     */
    private static final long serialVersionUID = 1L;

    /**
     * 分类的id
     * @return category_id 分类的id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 分类的id
     * @param categoryId 分类的id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 分类的名称
     * @return title 分类的名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 分类的名称
     * @param title 分类的名称
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 分类是否推荐（前台首页界面展示）
     * @return weight 分类是否推荐（前台首页界面展示）
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 分类是否推荐（前台首页界面展示）
     * @param weight 分类是否推荐（前台首页界面展示）
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 首页推荐
     * @return support 首页推荐
     */
    public Boolean getSupport() {
        return support;
    }

    /**
     * 首页推荐
     * @param support 首页推荐
     */
    public void setSupport(Boolean support) {
        this.support = support;
    }
}