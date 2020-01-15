package com.dimple.project.log.service;

import com.dimple.project.log.domain.QuartzJobLog;

import java.util.List;

/**
 * @className: QuartzJobLogService
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface QuartzJobLogService {
    /**
     * 插入Job log
     *
     * @param quartzJobLog log
     */
    int insertQuartzJobLog(QuartzJobLog quartzJobLog);

    /**
     * 获取JobLog
     *
     * @param quartzJobLog 查询条件
     * @return list
     */
    List<QuartzJobLog> selectQuartzJobLogList(QuartzJobLog quartzJobLog);

    /**
     * 根据id删除日志
     *
     * @param ids id集合
     * @return 受影响的行数
     */
    int deleteQuartzJobLogByIds(String ids);

    /**
     * 清空日志
     */
    void cleanQuartzJobLog();

    /**
     * select quartz job log by id
     *
     * @param id id
     * @return quartzLog
     */
    QuartzJobLog selectQuartzJobLogById(Long id);
}
