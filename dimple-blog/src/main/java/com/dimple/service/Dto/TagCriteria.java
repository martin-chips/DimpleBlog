package com.dimple.service.Dto;

import com.dimple.annotation.Query;
import lombok.Data;

/**
 * @className: TagCriteria
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Data
public class TagCriteria {
    @Query(blurry = "title")
    private String blurry;
}
