package com.dimple.modules.linkManager.bean;

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
    private Boolean display;
    private Boolean status;
    private Integer weight;
    private Date createTime;
    private String url;
    private String description;
    private Integer click;
    private Boolean available;
}