package com.dimple.modules.endModule.logManager.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.logManager.bean.JobLog;
import com.dimple.modules.endModule.logManager.service.JobLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @className: JobLogController
 * @description: 定时任务日志的Controller
 * @auther: Owenb
 * @date: 01/30/19
 * @version: 1.0
 */
@Controller
@Api("定时任务日志管理")
public class JobLogController {

    @Autowired
    JobLogService jobLogService;

    @GetMapping("/page/jobLog.html")
    public String jobLogPage() {
        return "log/jobLog/jobLog";
    }

    @GetMapping("/page/jobLog/{id}.html")
    public String jobLogDetail(@PathVariable Long id, Model model) {
        model.addAttribute("jobLog", jobLogService.getJobLogById(id));
        return "log/jobLog/detail";
    }


    @GetMapping("/api/jobLog")
    @ResponseBody
    public Result jobLogList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "methodName", required = false) String methodName,
                             @RequestParam(value = "jobName", required = false) String jobName,
                             @RequestParam(value = "status", required = false) Boolean status,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<JobLog> allJobLog = jobLogService.getAllJobLog(methodName, jobName, status, startTime, endTime, pageable);
        return ResultUtil.success(allJobLog);
    }

    @DeleteMapping("/api/jobLog/{id}")
    @ResponseBody
    public Result deleteJobLogById(@PathVariable Long id[]) {
        jobLogService.deleteJobLogById(id);
        return ResultUtil.success();
    }


    @DeleteMapping("/api/jobLog")
    @ResponseBody
    public Result cleanJobLog() {
        jobLogService.cleanJobLog();
        return ResultUtil.success();
    }


}
