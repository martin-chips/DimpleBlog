package com.dimple.project.home.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.home.domain.LineChartData;
import com.dimple.project.home.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @className: DashboardController
 * @description: 后台管理首页controller
 * @author: Dimple
 * @date: 12/20/19
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

    final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/panelGroup")
    public AjaxResult getPanelGroupData() {
        Map<String, Long> panelGroupData = dashboardService.getPanelGroupData();
        return AjaxResult.success(panelGroupData);
    }

    @GetMapping("lineChartData/{type}")
    public AjaxResult getLineChartData(@PathVariable String type) {
        LineChartData<Long> lineChartData = dashboardService.getLineChartData(type);
        return AjaxResult.success(lineChartData);
    }

    @GetMapping("/spiderData")
    public AjaxResult getSpiderData() {
        List<Map<String, Long>> result = dashboardService.getSpiderData();
        return AjaxResult.success(result);
    }

    @GetMapping("/visitLog")
    public TableDataInfo visitLogDataInfo() {
        startPage();
        List<String> visitLogList = dashboardService.getVisitLogStringList();
        return getDataTable(visitLogList);
    }

    @GetMapping("/loginLog")
    public TableDataInfo loginLogDataInfo() {
        startPage();
        List<String> visitLogList = dashboardService.getLoginLogStringList();
        return getDataTable(visitLogList);
    }

    @GetMapping("/operateLog")
    public TableDataInfo operateLogDataInfo() {
        startPage();
        List<String> visitLogList = dashboardService.getOperateLogStringList();
        return getDataTable(visitLogList);
    }

    @GetMapping("/taskLog")
    public TableDataInfo taskLogDataInfo() {
        startPage();
        List<String> visitLogList = dashboardService.getTaskLogStringList();
        return getDataTable(visitLogList);
    }
}
