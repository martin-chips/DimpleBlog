package com.dimple.service.dto;

import com.dimple.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @className: PictureQueryCriteria
 * @description: sm.ms图床
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
public class PictureQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String filename;

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
