package com.dimple.job.service.util;

import com.dimple.job.service.service.bo.SysJobBO;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author Dimple
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJobBO sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
