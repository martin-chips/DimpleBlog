package com.dimple.service.Dto;

import com.dimple.base.BaseDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @className: TagDTO
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Data
@ToString(callSuper = true)
public class TagDTO extends BaseDTO implements Serializable {
    private Long id;
    private String title;
    private String color;
}
