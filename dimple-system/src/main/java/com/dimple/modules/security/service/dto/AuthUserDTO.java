package com.dimple.modules.security.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


/**
 * @className: AuthUserDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class AuthUserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

    @Override
    public String toString() {
        return "{username=" + username + ", password= ******}";
    }
}
