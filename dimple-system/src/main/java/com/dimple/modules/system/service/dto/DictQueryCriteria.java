package com.dimple.modules.system.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

/**
 * @className: DictQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class DictQueryCriteria {

    @Query(blurry = "name,description")
    private String blurry;
}
