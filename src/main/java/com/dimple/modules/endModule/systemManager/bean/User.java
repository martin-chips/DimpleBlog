package com.dimple.modules.endModule.systemManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : User
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -4531449501288568149L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    private String userName;
    private String userLoginId;
    private String password;
    private String salt;
    private Boolean locked;
    private String email;
    private String phone;
    private Byte sex;
    private Date createTime;
    @Transient
    private Integer roleId;

}