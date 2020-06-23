package com.dimple.service.Dto;

import com.dimple.annotation.Query;
import lombok.Data;

/**
 * @className: CategoryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Data
public class CategoryCriteria {
    @Query(blurry = "title,qqNum")
    private String blurry;
}
