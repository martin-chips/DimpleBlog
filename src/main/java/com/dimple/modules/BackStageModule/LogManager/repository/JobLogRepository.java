package com.dimple.modules.BackStageModule.LogManager.repository;

import com.dimple.modules.BackStageModule.LogManager.bean.JobLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @className: JobLogRepository
 * @description:
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
@Repository
public interface JobLogRepository extends JpaRepository<JobLog, Long>, JpaSpecificationExecutor<JobLog> {

    JobLog getByJobLogId(Long id);

}
