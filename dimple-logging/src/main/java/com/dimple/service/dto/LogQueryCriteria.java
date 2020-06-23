package com.dimple.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


/**
 * @className: LogQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class LogQueryCriteria {

    @Query(blurry = "username,description,address,requestIp,method,params")
    private String blurry;

    @Query
    private String logType;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
