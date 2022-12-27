package com.dimple.job.service.mapper;

import com.dimple.job.service.entity.SysJobLog;

import java.util.List;

/**
 * 调度任务日志信息 数据层
 *
 * @author Dimple
 */
public interface SysJobLogMapper {
    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    List<SysJobLog> selectJobLogList(SysJobLog jobLog);

    /**
     * 查询所有调度任务日志
     *
     * @return 调度任务日志列表
     */
    List<SysJobLog> selectJobLogAll();

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    SysJobLog selectJobLogById(Long jobLogId);

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     * @return 结果
     */
    int insertJobLog(SysJobLog jobLog);

    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    int deleteJobLogByIds(Long[] logIds);

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     * @return 结果
     */
    int deleteJobLogById(Long jobId);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
