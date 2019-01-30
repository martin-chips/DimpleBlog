package com.dimple.modules.endModule.logManager.service;

import com.dimple.modules.endModule.logManager.bean.JobLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @className: JobLogService
 * @description:
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
public interface JobLogService {
    void insertJobLog(JobLog jobLog);

    void deleteJobLogById(Long[] id);

    void cleanJobLog();

    Page<JobLog> getAllJobLog(String methodName, String jobName, Boolean status, Date startTime, Date endTime, Pageable pageable);

    JobLog getJobLogById(Long id);
}
