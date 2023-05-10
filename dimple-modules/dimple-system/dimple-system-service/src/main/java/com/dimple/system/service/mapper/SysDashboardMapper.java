package com.dimple.system.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * SysDashboardMapper
 *
 * @author Dimple
 */
public interface SysDashboardMapper {
    @Select("SELECT DATE(create_time) AS day, COUNT(*) AS count " +
            "FROM ${tableName}} " +
            "WHERE create_time >= #{startDay} AND create_time <= #{endDay} " +
            "GROUP BY DATE(create_time) " +
            "ORDER BY day ASC")
    @Results({
            @Result(column = "count", property = "count", javaType = Long.class)
    })
    List<Long> getCountByDay(@Param("tableName") String tableName, @Param("startDay") Date startDay, @Param("endDay") Date endDay);

}
