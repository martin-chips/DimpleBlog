package com.dimple.modules.system.service.dto;

import com.dimple.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @className: DictDetailDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class DictDetailDTO extends BaseDTO implements Serializable {

    private Long id;

    private DictSmallDTO dict;

    private String label;

    private String value;

    private Integer dictSort;
}
