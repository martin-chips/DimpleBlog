package com.dimple.modules.system.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @className: JobQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@NoArgsConstructor
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
