package com.dimple.job.service.service.impl;


import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.job.service.entity.SysJobLog;
import com.dimple.job.service.mapper.SysJobLogMapper;
import com.dimple.job.service.service.SysJobLogService;
import com.dimple.job.service.service.bo.SysJobLogBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务调度日志信息 服务层
 *
 * @author Dimple
 */
@Service
public class SysJobLogServiceImpl implements SysJobLogService {
    @Autowired
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLogBO> selectJobLogList(SysJobLogBO jobLog) {
        return BeanMapper.convertList(jobLogMapper.selectJobLogList(BeanMapper.convert(jobLog, SysJobLog.class)), SysJobLogBO.class);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param id 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLogBO selectJobLogById(Long id) {
        return BeanMapper.convert(jobLogMapper.selectJobLogById(id), SysJobLogBO.class);
    }

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLogBO jobLog) {
        jobLogMapper.insertJobLog(BeanMapper.convert(jobLog, SysJobLog.class));
    }

    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的数据ID
     * @return affected lines
     */
    @Override
    public int deleteJobLogByIds(Long[] logIds) {
        return jobLogMapper.deleteJobLogByIds(logIds);
    }

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId) {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }
}
