package com.dimple.project.monitor.job.util;

import com.dimple.common.constant.Constants;
import com.dimple.common.constant.ScheduleConstants;
import com.dimple.common.utils.ExceptionUtil;
import com.dimple.common.utils.StringUtils;
import com.dimple.common.utils.bean.BeanUtils;
import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.log.jobLog.domain.JobLog;
import com.dimple.project.log.jobLog.service.IJobLogService;
import com.dimple.project.monitor.job.domain.Job;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @className: AbstractQuartzJob
 * @description: 抽象quartz调用
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Slf4j
public abstract class AbstractQuartzJob implements org.quartz.Job {

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Job job = new Job();
        BeanUtils.copyBeanProp(job, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try {
            before(context, job);
            if (job != null) {
                doExecute(context, job);
            }
            after(context, job, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, job, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     */
    protected void before(JobExecutionContext context, Job job) {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context        工作执行上下文对象
     * @param sysScheduleJob 系统计划任务
     */
    protected void after(JobExecutionContext context, Job job, Exception e) {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final JobLog jobLog = new JobLog();
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setMethodName(job.getMethodName());
        jobLog.setMethodParams(job.getMethodParams());
        jobLog.setStartTime(startTime);
        jobLog.setEndTime(new Date());
        long runMs = jobLog.getEndTime().getTime() - jobLog.getStartTime().getTime();
        jobLog.setJobMessage(jobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            jobLog.setStatus(Constants.FAIL);
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            jobLog.setExceptionInfo(errorMsg);
        } else {
            jobLog.setStatus(Constants.SUCCESS);
        }

        // 写入数据库当中
        SpringUtils.getBean(IJobLogService.class).addJobLog(jobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param job  系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, Job job) throws Exception;
}
