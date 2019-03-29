package com.dimple.project.log.visitorLog.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.log.visitorLog.domain.VisitLog;
import com.dimple.project.log.visitorLog.service.VisitLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: VisitorController
 * @description: 访客日志Controller
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/log/visitLog")
public class VisitLogController extends BaseController {

    @Autowired
    VisitLogService visitLogService;

    @RequiresPermissions("log:visitLog:view")
    @GetMapping()
    public String record() {
        return "log/visitLog/visitLog";
    }

    @RequiresPermissions("log:visitLog:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(VisitLog visitLog) {
        startPage();
        List<VisitLog> list = visitLogService.selectVisitLogList(visitLog);
        return getDataTable(list);
    }

    @RequiresPermissions("log:visitLog:remove")
    @Log(title = "访问日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(visitLogService.deleteVisitLogByIds(ids));
    }

    @RequiresPermissions("log:visitLog:detail")
    @GetMapping("/detail/{visitId}")
    public String detail(@PathVariable("visitId") Integer visitId, Model model) {
        model.addAttribute("visit", visitLogService.selectVisitLogById(visitId));
        return "log/visitLog/detail";
    }

    @Log(title = "访问日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("log:visitLog:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        visitLogService.cleanVisitLog();
        return success();
    }
}
