package com.dimple.system.web.controller;

import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.service.entity.dashboard.Dashboard;
import com.dimple.system.service.entity.dashboard.DashboardCountItem;
import com.dimple.system.service.entity.dashboard.DashboardKeyValue;
import com.dimple.system.service.service.SysDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页看板
 *
 * @author Dimple
 */
@RestController
@RequestMapping("/dashboard")
public class SysDashboardController extends BaseController {
    @Autowired
    private SysDashboardService dashboardService;

    @GetMapping("count")
    @RequiresPermissions("system:dashboard:list")
    public AjaxResult getDashboardWeeklyCount() {
        Dashboard dashboard = dashboardService.getDashboardWeeklyCount();
        return success(dashboard);
    }


    @GetMapping("count/item")
    @RequiresPermissions("system:dashboard:list")
    public AjaxResult getCountNumber() {
        DashboardCountItem item = dashboardService.getDashboardCountItems();
        return success(item);
    }

    @GetMapping("spider")
    @RequiresPermissions("system:dashboard:list")
    public AjaxResult getSpiderCount() {
        List<DashboardKeyValue> spiders = dashboardService.getSpiderCount();
        return success(spiders);
    }

    @GetMapping("referer")
    @RequiresPermissions("system:dashboard:list")
    public AjaxResult getRefererCount() {
        List<DashboardKeyValue> refererCount = dashboardService.getRefererCount();
        return success(refererCount);
    }
}
