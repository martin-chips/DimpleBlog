package com.dimple.modules.system.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

/**
 * @className: DictDetailQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class DictDetailQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String label;

    @Query(propName = "name", joinName = "dict")
    private String dictName;
}
