package com.dimple.modules.endModule.systemMonitor.util;

import com.dimple.framework.constant.ScheduleConstants;
import com.dimple.modules.endModule.systemMonitor.bean.Job;
import com.dimple.utils.BeanUtil;
import com.dimple.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

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
        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("任务开始执行 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            ScheduleRunnable task = new ScheduleRunnable(job.getJobName(), job.getMethodName(), job.getMethodParams());
            Future<?> future = executor.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.info("任务执行结束 - 名称：{} 耗时：{} 毫秒", job.getJobName(), times);
        } catch (Exception e) {
            log.info("任务执行失败 - 名称：{} 方法：{}", job.getJobName(), job.getMethodName());
            log.error("任务执行异常  - ：", e);
            long times = System.currentTimeMillis() - startTime;
        }
    }
}
