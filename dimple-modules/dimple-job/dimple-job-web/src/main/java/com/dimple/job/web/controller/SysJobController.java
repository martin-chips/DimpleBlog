package com.dimple.job.web.controller;

import com.dimple.common.core.constant.Constants;
import com.dimple.common.core.exception.job.TaskException;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.utils.poi.ExcelUtil;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.page.TableDataInfo;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.log.annotation.OperationLog;
import com.dimple.common.log.enums.BusinessType;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.job.service.entity.SysJob;
import com.dimple.job.service.service.SysJobService;
import com.dimple.job.service.service.bo.SysJobBO;
import com.dimple.job.service.util.CronUtils;
import com.dimple.job.service.util.ScheduleUtils;
import com.dimple.job.web.controller.vo.SysJobVO;
import com.dimple.job.web.controller.vo.params.SysJobVOParamsParams;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 调度任务信息操作处理
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/job")
public class SysJobController extends BaseController {
    @Autowired
    private SysJobService jobService;

    /**
     * 查询定时任务列表
     */
    @RequiresPermissions("monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJobVOParamsParams sysJob) {
        startPage();
        SysJobBO sysJobBO = BeanMapper.convert(sysJob, SysJobBO.class);
        List<SysJobBO> list = jobService.selectJobList(sysJobBO);
        return getDataTable(BeanMapper.convertList(list, SysJobVO.class));
    }

    /**
     * 导出定时任务列表
     */
    @RequiresPermissions("monitor:job:export")
    @OperationLog(title = "定时任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJobVOParamsParams sysJob) {
        List<SysJobBO> list = jobService.selectJobList(BeanMapper.convert(sysJob, SysJobBO.class));
        ExcelUtil<SysJobVO> util = new ExcelUtil<>(SysJobVO.class);
        util.exportExcel(response, BeanMapper.convertList(list, SysJobVO.class), "定时任务");
    }

    /**
     * 获取定时任务详细信息
     */
    @RequiresPermissions("monitor:job:query")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
        return success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @RequiresPermissions("monitor:job:add")
    @OperationLog(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        } else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
        return toAjax(jobService.insertJob(BeanMapper.convert(job, SysJobBO.class)));
    }

    /**
     * 修改定时任务
     */
    @RequiresPermissions("monitor:job:edit")
    @OperationLog(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.LOOKUP_LDAP, Constants.LOOKUP_LDAPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), Constants.JOB_ERROR_STR)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        } else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
        return toAjax(jobService.updateJob(BeanMapper.convert(job, SysJobBO.class)));
    }

    /**
     * 定时任务状态修改
     */
    @RequiresPermissions("monitor:job:changeStatus")
    @OperationLog(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJobVOParamsParams job) throws SchedulerException {
        SysJobBO newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @RequiresPermissions("monitor:job:changeStatus")
    @OperationLog(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJobVOParamsParams job) throws SchedulerException {
        boolean result = jobService.run(BeanMapper.convert(job, SysJobBO.class));
        return result ? success() : error("任务不存在或已过期！");
    }

    /**
     * 删除定时任务
     */
    @RequiresPermissions("monitor:job:remove")
    @OperationLog(title = "定时任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException {
        jobService.deleteJobByIds(jobIds);
        return success();
    }
}
