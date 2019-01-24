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
 * @class : UserRole
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
@Table(name = "user_role", schema = "dimple_blog")
public class UserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private int roleId;
    private int userId;
}