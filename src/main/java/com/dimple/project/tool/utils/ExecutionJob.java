package com.dimple.project.tool.utils;

import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.log.domain.QuartzJobLog;
import com.dimple.project.log.service.QuartzJobLogService;
import com.dimple.project.tool.domain.QuartzJob;
import com.dimple.project.tool.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Future;

/**
 * @className: ExeJob
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Async
@Slf4j
@Component
public class ExecutionJob extends QuartzJobBean {

    ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtils.getBean("threadPoolTaskExecutor");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzJobService quartzJobService = SpringUtils.getBean(QuartzJobService.class);
        QuartzJobLogService quartzJobLogService = SpringUtils.getBean(QuartzJobLogService.class);

        QuartzJobLog quartzJobLog = new QuartzJobLog();
        quartzJobLog.setJobName(quartzJob.getJobName());
        quartzJobLog.setBeanName(quartzJob.getBeanName());
        quartzJobLog.setMethodName(quartzJob.getMethodName());
        quartzJobLog.setMethodParams(quartzJob.getMethodParams());
        quartzJobLog.setCronExpression(quartzJob.getCronExpression());

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getMethodParams());
            Future<Object> future = threadPoolTaskExecutor.submit(task);
            Object result = future.get();
            log.info("任务返回值:{}", result);
            quartzJobLog.setResult(result.toString());
            long times = System.currentTimeMillis() - startTime;
            quartzJobLog.setCost(times);
            // 任务状态
            quartzJobLog.setStatus(true);
            log.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            quartzJobLog.setCost(times);
            quartzJobLog.setStatus(false);
            quartzJobLog.setException(getStackTrace(e));
            //设置为暂停状态
            quartzJob.setStatus(false);
            //更新状态
            quartzJobService.updateQuartzJob(quartzJob);
        } finally {
            quartzJobLogService.insertQuartzJobLog(quartzJobLog);
        }
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
