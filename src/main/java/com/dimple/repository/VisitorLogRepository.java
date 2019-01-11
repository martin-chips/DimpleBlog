package com.dimple.repository;

import com.dimple.bean.VisitorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
