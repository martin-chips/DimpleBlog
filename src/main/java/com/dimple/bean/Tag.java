package com.dimple.bean;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

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
    private Integer id;
    private String title;
    private Date createTime;
    private Boolean status;
}