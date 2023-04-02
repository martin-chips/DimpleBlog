package com.dimple.job.service.service;

import com.dimple.job.service.service.bo.SysJobLogBO;

import java.util.List;

/**
 * 定时任务调度日志信息信息 服务层
 *
 * @author Dimple
 */
public interface SysJobLogService {
    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    List<SysJobLogBO> selectJobLogList(SysJobLogBO jobLog);

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param id 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    SysJobLogBO selectJobLogById(Long id);

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    void addJobLog(SysJobLogBO jobLog);

    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的日志ID
     * @return affected lines
     */
    int deleteJobLogByIds(Long[] logIds);

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     * @return affected lines
     */
    int deleteJobLogById(Long jobId);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
