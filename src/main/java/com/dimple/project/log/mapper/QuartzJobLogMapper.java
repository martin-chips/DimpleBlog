package com.dimple.project.log.mapper;

import com.dimple.project.log.domain.QuartzJobLog;

import java.util.List;

/**
 * @className: QuartzJobLogMapper
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
public interface QuartzJobLogMapper {
    /**
     * 插入Job log
     *
     * @param quartzJobLog log
     * @return 受影响的行数
     */
    int insertQuartzJobLog(QuartzJobLog quartzJobLog);

    /**
     * 根据条件查询Log
     *
     * @param quartzJobLog 条件
     * @return list
     */
    List<QuartzJobLog> selectQuartzJobLogList(QuartzJobLog quartzJobLog);
}
