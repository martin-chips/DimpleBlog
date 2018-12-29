package com.dimple.bean;

import com.dimple.framework.converter.BooleanToIntegerConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Objects;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : User
 * @description :
 * @date : 12/26/18 20:54
 */
@Entity
@Data
public class User {
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