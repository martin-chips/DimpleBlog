package com.dimple.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Rotation
 * @description :
 * @date : 01/03/19 20:39
 */
@Entity
@Data
public class Rotation {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String img;
    private String title;
    private String alt;
    private Timestamp createTime;
    private Boolean display;
}