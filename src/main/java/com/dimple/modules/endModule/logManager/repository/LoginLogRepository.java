package com.dimple.modules.endModule.logManager.repository;

import com.dimple.modules.endModule.logManager.bean.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : LoginLogRepository
 * @description :
 * @date : 12/26/18 19:11
 */
@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Integer>, JpaSpecificationExecutor<LoginLog> {

    @Query(value = "select (select count(*) from login_log) as total," +
            "(select count(*) from login_log where status = 1) as success, " +
            "(select count(*) from login_log where status = 0) as failure;", nativeQuery = true)
    Map<String, Integer> getStatusCount();

    LoginLog findByLogId(Integer id);
}
