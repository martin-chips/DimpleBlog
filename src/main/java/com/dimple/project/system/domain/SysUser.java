package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @className: SysUser
 * @description: 用户对象 sys_user
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUser extends BaseEntity implements Serializable {

    public SysUser(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐加密
     */
    private String salt;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date loginDate;

    /**
     * 角色对象
     */
    private List<Role> roles;

    /**
     * 角色组
     */
    private Long[] roleIds;

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
