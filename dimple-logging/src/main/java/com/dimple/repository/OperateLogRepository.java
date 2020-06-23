package com.dimple.repository;

import com.dimple.domain.OperateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @className: LogRepository
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Repository
public interface OperateLogRepository extends JpaRepository<OperateLog, Long>, JpaSpecificationExecutor<OperateLog> {

    /**
     * 根据日志类型删除信息
     *
     * @param logType 日志类型
     */
    @Modifying
    @Query(value = "delete from sys_log where log_type = ?1", nativeQuery = true)
    void deleteByLogType(String logType);
}
