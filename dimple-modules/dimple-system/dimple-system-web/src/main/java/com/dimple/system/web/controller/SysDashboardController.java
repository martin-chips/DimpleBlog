package com.dimple.system.web.controller;

import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.system.service.entity.dashboard.Dashboard;
import com.dimple.system.service.service.SysDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AjaxResult getDashboardWeeklyCount() {
        Dashboard dashboard = dashboardService.getDashboardWeeklyCount();
        return success(dashboard);
    }
}
