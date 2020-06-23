package com.dimple.domain;

import com.dimple.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @className: LoginLog
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Entity
@Table(name = "sys_login_log")
public class LoginLog extends BaseEntity implements Serializable {

    @Id
    @Column(name = "login_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String requestIp;

    private String address;

    private String browser;

    private String os;

    private boolean status;

    private String msg;
}
