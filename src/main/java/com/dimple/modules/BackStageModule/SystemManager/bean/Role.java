package com.dimple.modules.BackStageModule.SystemManager.bean;

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
 * @class : Role
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    private String roleName;
    private String description;
    private Boolean locked;
    private Date createTime;

}