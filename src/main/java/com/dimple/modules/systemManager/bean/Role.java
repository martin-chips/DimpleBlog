package com.dimple.modules.systemManager.bean;

import com.dimple.framework.converter.BooleanToIntegerConverter;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

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