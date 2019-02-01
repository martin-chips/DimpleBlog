package com.dimple.modules.BackStageModule.BlogManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : BlogInfo
 * @description :
 * @date : 12/27/18 16:50
 */
@Entity
@Table(name = "blog_info", schema = "dimple_blog")
@Data
public class BlogInfo {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private int blogId;
    private String content;

}
