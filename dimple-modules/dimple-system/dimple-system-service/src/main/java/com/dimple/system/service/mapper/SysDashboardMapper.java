package com.dimple.system.service.mapper;

import com.dimple.system.service.entity.dashboard.DashboardKeyValue;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * SysDashboardMapper
 *
 * @author Dimple
 */
public interface SysDashboardMapper {
    @Select("SELECT COUNT(*) AS count " +
            "FROM ${tableName} " +
            "WHERE date(create_time) = #{startDay} ")
    @Results({
            @Result(column = "count", property = "count", javaType = Long.class)
    })
    Long getCountByDay(@Param("tableName") String tableName, @Param("startDay") String startDay);


    @Select("SELECT COUNT(*) AS count " +
            "FROM ${tableName} ")
    @Results({
            @Result(column = "count", property = "count", javaType = Long.class)
    })
    Long getCountItem(@Param("tableName") String tableName);

    @Select("SELECT spider, COUNT(*) AS count FROM blog_visit_log WHERE spider IS NOT NULL GROUP BY spider")
    @Results({
            @Result(column = "spider", property = "name"),
            @Result(column = "count", property = "value")
    })
    List<DashboardKeyValue> getSpiderCount();


    @Select("select SUBSTRING_INDEX(REPLACE(REPLACE(referer, 'http://', ''), 'https://', ''), '/', 1) AS host,count(*) as count from blog_visit_log group by host order by count desc limit 10")
    @Results({
            @Result(column = "host", property = "name"),
            @Result(column = "count", property = "value")
    })
    List<DashboardKeyValue> getReferCount();
}
