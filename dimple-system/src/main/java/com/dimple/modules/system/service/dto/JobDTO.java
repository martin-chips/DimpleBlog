package com.dimple.modules.system.service.dto;

import com.dimple.base.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @className: JobDTO
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Getter
@Setter
@NoArgsConstructor
public class JobDTO extends BaseDTO implements Serializable {

    private Long id;

    private Integer jobSort;

    private String name;

    private Boolean enabled;

    public JobDTO(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
