package com.dimple.modules.BackStageModule.BlogManager.bean;

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
 * @class : TagRepository
 * @description : 标签
 * @date : 12/28/18 20:15
 */
@Entity
@Data
public class Tag {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Date createTime;
}