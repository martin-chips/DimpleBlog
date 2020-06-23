package com.dimple.modules.system.service.dto;

import com.dimple.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @className: DictDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
public class DictDTO extends BaseDTO implements Serializable {

    private Long id;

    private List<DictDetailDTO> dictDetails;

    private String name;

    private String description;
}
