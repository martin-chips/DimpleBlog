package com.dimple.job.service.service;

import com.dimple.common.core.exception.job.TaskException;
import com.dimple.job.service.service.bo.SysJobBO;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 定时任务调度信息信息 服务层
 *
 * @author Dimple
 */
 public interface SysJobService {
    /**
     * 获取quartz调度器的计划任务
     *
     * @param job 调度信息
     * @return 调度任务集合
     */
     List<SysJobBO> selectJobList(SysJobBO job);

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param id 调度任务ID
     * @return 调度任务对象信息
     */
    SysJobBO selectJobById(Long id);

    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return affected lines
     */
     int pauseJob(SysJobBO job) throws SchedulerException;

    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return affected lines
     */
     int resumeJob(SysJobBO job) throws SchedulerException;

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     * @return affected lines
     */
     int deleteJob(SysJobBO job) throws SchedulerException;

    /**
     * 批量删除调度信息
     *
     * @param ids 需要删除的任务ID
     * @return affected lines
     */
     void deleteJobByIds(Long[] ids) throws SchedulerException;

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     * @return affected lines
     */
     int changeStatus(SysJobBO job) throws SchedulerException;

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     * @return affected lines
     */
     boolean run(SysJobBO job) throws SchedulerException;

    /**
     * 新增任务
     *
     * @param job 调度信息
     * @return affected lines
     */
     int insertJob(SysJobBO job) throws SchedulerException, TaskException;

    /**
     * 更新任务
     *
     * @param job 调度信息
     * @return affected lines
     */
     int updateJob(SysJobBO job) throws SchedulerException, TaskException;

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return affected lines
     */
     boolean checkCronExpressionIsValid(String cronExpression);
}