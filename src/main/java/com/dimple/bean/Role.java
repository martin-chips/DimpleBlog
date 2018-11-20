package com.dimple.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Role implements Serializable {
    /**
     * 角色的id
     */
    private Integer roleId;

    /**
     * 角色的名称，如：admin
     */
    private String name;

    /**
     * 角色的描述，如：管理员
     */
    private String description;

    /**
     * role
     */
    private static final long serialVersionUID = 1L;

}