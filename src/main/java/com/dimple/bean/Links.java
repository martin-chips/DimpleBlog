package com.dimple.bean;

import java.io.Serializable;

public class Links implements Serializable {
    /**
     * id
     */
    private Integer linkId;

    /**
     * 友链的标题
     */
    private String title;

    /**
     * 友链的内容
     */
    private String content;

    /**
     * 友链的描述
     */
    private String description;

    /**
     * 是否显示
     */
    private Boolean linkDisplay;

    /**
     * 是否已经处理
     */
    private Boolean linkHandle;

    /**
     * 权重
     */
    private Integer linkWeight;

    /**
     * 友链链接地址
     */
    private String url;

    /**
     * links
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return link_id id
     */
    public Integer getLinkId() {
        return linkId;
    }

    /**
     * id
     * @param linkId id
     */
    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * 友链的标题
     * @return title 友链的标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 友链的标题
     * @param title 友链的标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 友链的内容
     * @return content 友链的内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 友链的内容
     * @param content 友链的内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 友链的描述
     * @return description 友链的描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 友链的描述
     * @param description 友链的描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 是否显示
     * @return link_display 是否显示
     */
    public Boolean getLinkDisplay() {
        return linkDisplay;
    }

    /**
     * 是否显示
     * @param linkDisplay 是否显示
     */
    public void setLinkDisplay(Boolean linkDisplay) {
        this.linkDisplay = linkDisplay;
    }

    /**
     * 是否已经处理
     * @return link_handle 是否已经处理
     */
    public Boolean getLinkHandle() {
        return linkHandle;
    }

    /**
     * 是否已经处理
     * @param linkHandle 是否已经处理
     */
    public void setLinkHandle(Boolean linkHandle) {
        this.linkHandle = linkHandle;
    }

    /**
     * 权重
     * @return link_weight 权重
     */
    public Integer getLinkWeight() {
        return linkWeight;
    }

    /**
     * 权重
     * @param linkWeight 权重
     */
    public void setLinkWeight(Integer linkWeight) {
        this.linkWeight = linkWeight;
    }

    /**
     * 友链链接地址
     * @return url 友链链接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 友链链接地址
     * @param url 友链链接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
}