package com.dimple.job.service.service.impl;

import com.dimple.common.core.constant.ScheduleConstants;
import com.dimple.common.core.exception.job.TaskException;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.job.service.entity.SysJob;
import com.dimple.job.service.mapper.SysJobMapper;
import com.dimple.job.service.service.SysJobService;
import com.dimple.job.service.service.bo.SysJobBO;
import com.dimple.job.service.util.CronUtils;
import com.dimple.job.service.util.ScheduleUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 *
 * @author Dimple
 */
@Service
public class SysJobServiceImpl implements SysJobService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper jobMapper;

    /**
     * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        scheduler.clear();
        List<SysJobBO> jobList = BeanMapper.convertList(jobMapper.selectJobAll(), SysJobBO.class);
        for (SysJobBO job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     *
     * @param job 调度信息
     * @return
     */
    @Override
    public List<SysJobBO> selectJobList(SysJobBO job) {
        return BeanMapper.convertList(jobMapper.selectJobList(BeanMapper.convert(job, SysJob.class)), SysJobBO.class);
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param id 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public SysJobBO selectJobById(Long id) {
        return BeanMapper.convert(jobMapper.selectJobById(id), SysJobBO.class);
    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pauseJob(SysJobBO job) throws SchedulerException {
        SysJob sysJob = BeanMapper.convert(job, SysJob.class);
        Long id = sysJob.getId();
        String jobGroup = sysJob.getJobGroup();
        sysJob.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.updateJob(sysJob);
        if (rows > 0) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(id, jobGroup));
        }
        return rows;
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resumeJob(SysJobBO job) throws SchedulerException {
        SysJob sysJob = BeanMapper.convert(job, SysJob.class);
        Long id = sysJob.getId();
        String jobGroup = sysJob.getJobGroup();
        sysJob.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = jobMapper.updateJob(sysJob);
        if (rows > 0) {
            scheduler.resumeJob(ScheduleUtils.getJobKey(id, jobGroup));
        }
        return rows;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteJob(SysJobBO job) throws SchedulerException {
        Long id = job.getId();
        String jobGroup = job.getJobGroup();
        int rows = jobMapper.deleteJobById(id);
        if (rows > 0) {
            scheduler.deleteJob(ScheduleUtils.getJobKey(id, jobGroup));
        }
        return rows;
    }

    /**
     * 批量删除调度信息
     *
     * @param ids 需要删除的任务ID
     * @return affected lines
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(Long[] ids) throws SchedulerException {
        for (Long id : ids) {
            SysJob job = jobMapper.selectJobById(id);
            deleteJob(BeanMapper.convert(job, SysJobBO.class));
        }
    }

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeStatus(SysJobBO job) throws SchedulerException {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean run(SysJobBO job) throws SchedulerException {
        boolean result = false;
        Long id = job.getId();
        String jobGroup = job.getJobGroup();
        SysJobBO properties = selectJobById(job.getId());
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        JobKey jobKey = ScheduleUtils.getJobKey(id, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            result = true;
            scheduler.triggerJob(jobKey, dataMap);
        }
        return result;
    }

    /**
     * 新增任务
     *
     * @param job 调度信息 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertJob(SysJobBO job) throws SchedulerException, TaskException {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        SysJob sysJob = BeanMapper.convert(job, SysJob.class);
        int rows = jobMapper.insertJob(sysJob);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 更新任务的时间表达式
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateJob(SysJobBO job) throws SchedulerException, TaskException {
        SysJobBO properties = selectJobById(job.getId());
        SysJob sysJob = BeanMapper.convert(job, SysJob.class);
        int rows = jobMapper.updateJob(sysJob);
        if (rows > 0) {
            updateSchedulerJob(job, properties.getJobGroup());
        }
        return rows;
    }

    /**
     * 更新任务
     *
     * @param job      任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(SysJobBO job, String jobGroup) throws SchedulerException, TaskException {
        Long id = job.getId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(id, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return affected lines
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}