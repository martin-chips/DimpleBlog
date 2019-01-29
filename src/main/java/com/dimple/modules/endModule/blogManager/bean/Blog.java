package com.dimple.modules.endModule.blogManager.bean;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Blog
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Blog implements Serializable {
    @Id
    @Column(name = "blog_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    private Integer categoryId;
    private String title;
    private String summary;
    @Temporal(TemporalType.DATE)
    private Date createTime;
    private Integer status;
    private Boolean support;
    private Integer click;
    private Integer weight;
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    private String headerUrl;
    @Transient
    private String content;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private BlogInfo blogInfo;

    @Transient
    private String categoryName;
    @Transient
    private String[] tags;
}