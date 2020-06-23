package com.dimple.service.Dto;

import com.dimple.base.BaseDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @className: CategoryDTO
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Data
@ToString(callSuper = true)
public class CategoryDTO extends BaseDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Boolean support;
}
