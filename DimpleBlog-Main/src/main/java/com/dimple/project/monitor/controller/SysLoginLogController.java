package com.dimple.project.monitor.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.monitor.domain.SysLoginLog;
import com.dimple.project.monitor.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: SysLoginLogController
 * @description: 系统访问记录
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@RequestMapping("/monitor/loginLog")
public class SysLoginLogController extends BaseController {
    @Autowired
    private SysLoginLogService loginLogService;

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginLog loginLog) {
        startPage();
        List<SysLoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }
}
