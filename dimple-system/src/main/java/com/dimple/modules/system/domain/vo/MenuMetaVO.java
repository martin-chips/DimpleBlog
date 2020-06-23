package com.dimple.modules.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: MenuMetaVO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@AllArgsConstructor
public class MenuMetaVO implements Serializable {

    private String title;

    private String icon;

    private Boolean noCache;
}
