package com.dimple.bean;

import com.dimple.framework.converter.BooleanToIntegerConverter;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Visitor
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Visitor {
    @Id
    @Column(name = "visitor_id", nullable = false)
    private int visitorId;
    private String address;
    private String browser;
    private String ip;
    private Boolean locked;
    private Date visitTime;
}