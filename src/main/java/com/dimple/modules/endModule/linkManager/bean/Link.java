package com.dimple.modules.endModule.linkManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Link
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Link {

    @Id
    @Column(name = "link_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer linkId;
    private String title;
    private String description;
    //网站头像地址
    private String headerUrl;
    private String url;
    private Integer click=0;
    private Integer weight=0;
    private Boolean handle;
    private Boolean display;
    private Boolean available=true;
    private Date createTime;
}