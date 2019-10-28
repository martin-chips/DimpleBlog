package com.dimple.project.monitor.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.monitor.domain.OperateLog;
import com.dimple.project.monitor.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: OperateLogController
 * @description: 操作日志记录
 * @author: Dimple
 * @date: 10/22/19
 */
@RestController
@RequestMapping("/monitor/operateLog")
public class OperateLogController extends BaseController {
    @Autowired
    private OperateLogService operateLogService;

    @PreAuthorize("@permissionService.hasPermission('monitor:operateLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(OperateLog operateLog) {
        startPage();
        List<OperateLog> list = operateLogService.selectOperateLogList(operateLog);
        return getDataTable(list);
    }
}
