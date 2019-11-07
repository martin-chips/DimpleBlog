package com.dimple.project.monitor.service;

import com.dimple.project.tool.domain.QuartzJobLog;

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

}
