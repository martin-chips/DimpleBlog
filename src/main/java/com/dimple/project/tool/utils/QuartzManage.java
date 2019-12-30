package com.dimple.project.tool.utils;

import com.dimple.common.exception.CustomException;
import com.dimple.project.tool.domain.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @className: QuartzManage
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@Component
@Slf4j
public class QuartzManage {
    private static final String JOB_NAME = "TASK_";

    @Autowired
    Scheduler scheduler;

    /**
     * 添加任务
     *
     * @param quartzJob
     */
    public void addJob(QuartzJob quartzJob) {
        try {
            log.info("开始创建任务{}", quartzJob.getJobName());
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                    withIdentity(JOB_NAME + quartzJob.getId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();

            cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);

            //重置启动时间
            ((CronTriggerImpl) cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail, cronTrigger);

            // 暂停任务
            if (!quartzJob.getStatus().booleanValue()) {
                pauseJob(quartzJob);
            }
        } catch (Exception e) {
            log.error("创建定时任务失败", e);
            throw new CustomException("创建定时任务失败");
        }
    }

    /**
     * 更新job cron表达式
     */
    public void updateJobCron(QuartzJob quartzJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null) {
                addJob(quartzJob);
                trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置启动时间
            ((CronTriggerImpl) trigger).setStartTime(new Date());
            trigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);

            scheduler.rescheduleJob(triggerKey, trigger);
            // 暂停任务
            if (!quartzJob.getStatus().booleanValue()) {
                pauseJob(quartzJob);
            }
        } catch (Exception e) {
            log.error("更新定时任务失败", e);
            throw new CustomException("更新定时任务失败");
        }

    }

    /**
     * 删除一个job
     */
    public void deleteJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e) {
            log.error("删除定时任务失败", e);
            throw new CustomException("删除定时任务失败");
        }
    }

    /**
     * 恢复一个job
     */
    public void resumeJob(QuartzJob quartzJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null)
                addJob(quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e) {
            log.error("恢复定时任务失败", e);
            throw new CustomException("恢复定时任务失败");
        }
    }

    /**
     * 立即执行job
     */
    public void runAJobNow(QuartzJob quartzJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if (trigger == null)
                addJob(quartzJob);
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(QuartzJob.JOB_KEY, quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.triggerJob(jobKey, dataMap);
        } catch (Exception e) {
            log.error("定时任务执行失败", e);
            throw new CustomException("定时任务执行失败");
        }
    }

    /**
     * 暂停一个job
     *
     * @param quartzJob /
     */
    public void pauseJob(QuartzJob quartzJob) {
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            log.error("定时任务暂停失败", e);
            throw new CustomException("定时任务暂停失败");
        }
    }

}
