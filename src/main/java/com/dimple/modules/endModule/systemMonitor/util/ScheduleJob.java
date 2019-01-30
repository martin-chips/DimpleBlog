package com.dimple.modules.endModule.systemMonitor.util;

import com.dimple.framework.constant.ScheduleConstants;
import com.dimple.modules.endModule.logManager.bean.JobLog;
import com.dimple.modules.endModule.logManager.service.JobLogService;
import com.dimple.modules.endModule.systemMonitor.bean.Job;
import com.dimple.utils.BeanUtil;
import com.dimple.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * @className: ScheduleJob
 * @description: 定时任务
 * @auther: Owenb
 * @date: 01/29/19
 * @version: 1.0
 */
@DisallowConcurrentExecution
@Slf4j
public class ScheduleJob extends QuartzJobBean {


    private ThreadPoolTaskExecutor executor = SpringUtil.getBean("threadPoolTaskExecutor");


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Job job = new Job();
        BeanUtil.copyBeanProp(job, jobExecutionContext.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));

        JobLog jobLog = new JobLog();
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setMethodName(job.getMethodName());
        jobLog.setMethodParams(job.getMethodParams());
        jobLog.setCreateTime(new Date());

        JobLogService jobLogService = (JobLogService) SpringUtil.getBean(JobLogService.class);

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("任务开始执行 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(), job.getMethodName(), job.getMethodParams());
            Future<?> future = executor.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            jobLog.setStatus(true);
            jobLog.setJobMessage(job.getJobName() + " 总共耗时 " + times + " 毫秒");
            log.info("任务执行结束 - 名称：{} 耗时：{} 毫秒", job.getJobName(), times);
        } catch (Exception e) {
            log.info("任务执行失败 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            log.error("任务执行异常  - ：", e);
            long times = System.currentTimeMillis() - startTime;
            jobLog.setJobMessage(job.getJobName() + " 总共耗时：" + times + "毫秒");
            // 任务状态 0：成功 1：失败
            jobLog.setStatus(false);
            jobLog.setExceptionInfo(StringUtils.substring(e.getMessage(), 0, 2000));
        } finally {
            jobLogService.insertJobLog(jobLog);
        }
    }
}
