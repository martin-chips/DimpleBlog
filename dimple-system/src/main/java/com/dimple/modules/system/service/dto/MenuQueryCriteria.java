package com.dimple.modules.system.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @className: MenuQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class MenuQueryCriteria {

    @Query(blurry = "title,component,permission")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @Query
    private Long pid;
}
