package com.dimple.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @className: QiniuQueryCriteria
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class QiniuQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String key;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
