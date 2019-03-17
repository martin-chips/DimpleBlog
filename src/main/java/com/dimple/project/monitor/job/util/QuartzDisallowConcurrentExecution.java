package com.dimple.project.monitor.job.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import com.dimple.project.monitor.job.domain.Job;

/**
 * @className: QuartzDisallowConcurrentExecution
 * @description: 定时任务处理（禁止并发执行）
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
