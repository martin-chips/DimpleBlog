package com.dimple.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


/**
 * @className: VisitLogQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class VisitLogQueryCriteria {

    @Query(blurry = "title,status,address,requestIp,browser,os")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
