package com.dimple.modules.BackStageModule.LogManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : LoginLog
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Table(name = "login_log", schema = "dimple_blog")
@Data
public class LoginLog {
    @Id
    @Column(name = "log_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;
    private String loginName;
    private Boolean status;
    private String ipAddress;
    private String loginLocation;
    private String browser;
    private String os;
    private String msg;
    private Date loginTime;
}