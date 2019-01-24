package com.dimple.modules.endModule.systemManager.bean;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : RolePermission
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Table(name = "role_permission", schema = "dimple_blog")
@Data
public class RolePermission {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int roleId;
    private int permissionId;
}
