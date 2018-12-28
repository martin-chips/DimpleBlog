package com.dimple.bean;

import com.dimple.framework.converter.BooleanToIntegerConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Category
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String title;
    private Date createTime;
    private Date updateTime;
    private Integer weight;
    private String description;
    private Boolean support;

}