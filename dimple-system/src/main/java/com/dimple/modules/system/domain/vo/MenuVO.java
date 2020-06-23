package com.dimple.modules.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @className: MenuVO
 * @description: 构建前端路由时用到
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuVO implements Serializable {

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMetaVO meta;

    private List<MenuVO> children;
}
