package com.dimple.project.monitor.service.impl;

import com.dimple.project.monitor.mapper.QuartzJobLogMapper;
import com.dimple.project.monitor.service.QuartzJobLogService;
import com.dimple.project.tool.domain.QuartzJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: QuartzJobLogServiceImpl
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Service
public class QuartzJobLogServiceImpl implements QuartzJobLogService {

    @Autowired
    QuartzJobLogMapper quartzJobLogMapper;

    @Override
    public int insertQuartzJobLog(QuartzJobLog quartzJobLog) {
        return quartzJobLogMapper.insertQuartzJobLog(quartzJobLog);
    }
}
