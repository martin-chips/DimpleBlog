package com.dimple.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: RoleSmallDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class RoleSmallDTO implements Serializable {

    private Long id;

    private String name;

    private Integer level;
}
