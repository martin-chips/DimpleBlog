package com.dimple.modules.quartz.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


/**
 * @className: JobQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    private Boolean isSuccess;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
