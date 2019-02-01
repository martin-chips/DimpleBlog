package com.dimple.modules.BackStageModule.SystemMonitor.service;

import com.dimple.modules.BackStageModule.SystemMonitor.bean.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @className: JobService
 * @description:
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
public interface JobService {
    /**
     * 检验Cron表达式是否有效
     *
     * @param expression cron 表达式
     * @return 是否有效
     */
    Boolean checkCronExpression(String expression);

    /**
     * 根据Job的id删除Job（批量删除）
     *
     * @param ids
     */
    void deleteJobById(Long[] ids);

    /**
     * 暂停任务
     *
     * @param job
     * @return
     */
    void  pauseJob(Long id);

    /**
     * 恢复任务
     *
     * @param job
     * @return
     */
    void resumeJob(Long id);

    /**
     * 立即执行任务
     *
     * @param job
     * @return
     */
    void run(Long id);


    Job getJobById(Long id);

    Page<Job> getAllJob(Pageable pageable, String jobName, String methodName, Integer status);

    void changStatus(Long id, Integer status);

    void insertJob(Job job);

    void updateJob(Job job);
}
