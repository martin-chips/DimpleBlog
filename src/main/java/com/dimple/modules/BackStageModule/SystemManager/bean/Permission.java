package com.dimple.modules.BackStageModule.SystemManager.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : Permission
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class Permission {
    @Id
    @Column(name = "permission_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;
    private String title;
    private String name;
    private Integer pId;
    private String requestUrl;
    private Integer type;
    private String icon;
    private Boolean status;
}