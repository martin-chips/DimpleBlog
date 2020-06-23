package com.dimple.service.Dto;

import com.dimple.annotation.Query;
import lombok.Data;

/**
 * @className: BlogCriteria
 * @description:
 * @author: Dimple
 * @date: 06/18/20
 */
@Data
public class BlogCriteria {
    @Query(blurry = "title,summary")
    private String blurry;
}
