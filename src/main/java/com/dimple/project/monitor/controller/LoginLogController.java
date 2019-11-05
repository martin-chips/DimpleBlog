package com.dimple.project.monitor.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.monitor.domain.LoginLog;
import com.dimple.project.monitor.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: LoginLogController
 * @description: 系统访问记录
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@RequestMapping("/monitor/loginLog")
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginLogService;

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(LoginLog loginLog) {
        startPage();
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:query')")
    @GetMapping("/{pageNum}")
    public TableDataInfo queryCurrentUserLoginLog(@PathVariable Integer pageNum) {
        startPage(pageNum, null);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserName(SecurityUtils.getUsername());
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

}
