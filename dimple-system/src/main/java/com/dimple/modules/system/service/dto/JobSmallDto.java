package com.dimple.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: JobSmallDto
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@NoArgsConstructor
public class JobSmallDto implements Serializable {

    private Long id;

    private String name;
}
