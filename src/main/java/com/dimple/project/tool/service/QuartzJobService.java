package com.dimple.project.tool.service;

import com.dimple.project.tool.domain.QuartzJob;

import java.util.List;

/**
 * @className: QuartzService
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface QuartzJobService {
    /**
     * 新增定时任务
     *
     * @param quartzJob 定时任务
     * @return 受影响的行数
     */
    int insertQuartzJob(QuartzJob quartzJob);

    /**
     * 更新Job
     *
     * @param quartzJob job实体
     * @return 受影响的行数
     */
    int updateQuartzJob(QuartzJob quartzJob);

    /**
     * 根据id删除Job
     *
     * @param id id
     * @return 受影响的行数
     */
    int deleteQuartzJob(Long id);

    /**
     * 执行Job
     *
     * @param id id
     */
    void executeQuartzJobById(Long id);

    /**
     * 更新Job的状态(暂停,运行)
     *
     * @param id job的id
     * @return 受影响的行数
     */
    int updateQuartzJobStatus(Long id);

    /**
     * 获取Job list
     *
     * @param quartzJob 查询条件实体
     * @return job list
     */
    List<QuartzJob> selectQuartzJobList(QuartzJob quartzJob);

    /**
     * 根据id查询Job
     *
     * @param id id
     * @return job
     */
    QuartzJob selectQuartzJobById(Long id);

    /**
     * 获取运行状态为true的job
     *
     * @return job list
     */
    List<QuartzJob> selectRunningQuartzJobList();

}
