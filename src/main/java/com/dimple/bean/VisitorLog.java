package com.dimple.bean;

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
 * @class : VLog
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class VisitorLog {
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
    //请求的模块
    private String title;
    //请求的博文的ID
    private Integer blogId;
    private Date visitTime;
    private Boolean status;
}