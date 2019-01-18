package com.dimple.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : VLog
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Visitor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String sessionId;
    private String address;
    private String browser;
    private String os;
    private String ip;
    private String spider;
    private String requireUrl;
    private String errorMsg;
    //请求的模块
    private String title;
    //请求的博文的ID
    private Integer blogId;
    private Date visitTime;
    private Boolean status;
    @Transient
    private int count;
}