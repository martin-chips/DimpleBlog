package com.dimple.project.log.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.log.domain.LoginLog;
import com.dimple.project.log.service.LoginLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/log/loginLog")
public class LoginLogController extends BaseController {
    private final LoginLogService loginLogService;

    public LoginLogController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(LoginLog loginLog) {
        startPage();
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:query')")
    @GetMapping()
    public TableDataInfo queryCurrentUserLoginLog(LoginLog loginLog) {
        startPage();
        loginLog.setUserName(SecurityUtils.getUsername());
        List<LoginLog> list = loginLogService.selectLoginLogList(loginLog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:remove')")
    @DeleteMapping("{ids}")
    public AjaxResult deleteLoginLog(@PathVariable String ids) {
        return toAjax(loginLogService.deleteLoginLogByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('monitor:loginLog:remove')")
    @DeleteMapping("/clean")
    public AjaxResult cleanLoginLog() {
        loginLogService.cleanLoginLog();
        return AjaxResult.success();
    }
}
