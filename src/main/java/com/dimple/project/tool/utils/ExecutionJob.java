package com.dimple.project.tool.utils;

import com.dimple.common.utils.spring.SpringUtils;
import com.dimple.project.tool.domain.QuartzJob;
import com.dimple.project.tool.service.QuartzJonService;
import io.netty.util.internal.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.QuartzServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @className: ExeJob
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Async
@Slf4j
public class ExecutionJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        QuartzJob quartzJob = (QuartzJob) context.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzJonService quartzJobService = SpringUtils.getBean(QuartzJonService.class);

        //QuartzLog log = new QuartzLog();
        //log.setJobName(quartzJob.getJobName());
        //log.setBeanName(quartzJob.getBeanName());
        //log.setMethodName(quartzJob.getMethodName());
        //log.setParams(quartzJob.getParams());
        //long startTime = System.currentTimeMillis();
        //log.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            log.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = executor.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态
            log.setIsSuccess(true);
            logger.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
        } catch (Exception e) {
            logger.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            log.setTime(times);
            // 任务状态 0：成功 1：失败
            log.setIsSuccess(false);
            log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            quartzJob.setIsPause(false);
            //更新状态
            quartzJobService.updateIsPause(quartzJob);
        } finally {
            quartzLogRepository.save(log);
        }
    }
}
