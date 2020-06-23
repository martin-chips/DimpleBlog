package com.dimple.service.dto;

import com.dimple.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @className: LocalStorageDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class LocalStorageDTO extends BaseDTO implements Serializable {

    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String type;

    private String size;
}
