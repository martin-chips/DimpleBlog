package com.dimple.modules.system.service.dto;

import com.dimple.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @className: UserDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class UserDTO extends BaseDTO implements Serializable {

    private Long id;

    private Set<RoleSmallDTO> roles;

    private Set<JobSmallDto> jobs;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JsonIgnore
    private String password;

    private Boolean enabled;

    @JsonIgnore
    private Boolean isAdmin = false;

    private Date pwdResetTime;
}
