package com.dimple.job.service.util;

import com.dimple.job.service.service.bo.SysJobBO;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 *
 * @author Dimple
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJobBO sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
