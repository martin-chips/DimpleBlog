package com.dimple.repository;

import com.dimple.bean.VisitorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : VisitorLogRepository
 * @description :
 * @date : 12/26/18 19:15
 */
public interface VisitorLogRepository extends JpaRepository<VisitorLog, Integer> {

    @Query("from VisitorLog where id=:id")
    VisitorLog getById(@Param("id") Integer id);

    @Query(value = "select spider as 'name',count(spider) as 'value' from visitor_log group by spider order by spider limit 0,5", nativeQuery = true)
    List<Map<String, Integer>> getSpiderCount();

    @Query(value = "select count(*) from visitor_log where date(visit_time)=:date", nativeQuery = true)
    Integer getVisitorCountByVisitTime(@Param("date") String date);
}
