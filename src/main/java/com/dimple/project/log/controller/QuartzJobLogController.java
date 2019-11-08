package com.dimple.project.log.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.log.domain.QuartzJobLog;
import com.dimple.project.log.service.QuartzJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: QuartzJobLogController
 * @description:
 * @author: Dimple
 * @date: 11/08/19
 */
@RestController
@RequestMapping("/log/quartzLog")
public class QuartzJobLogController extends BaseController {

    @Autowired
    QuartzJobLogService quartzJobLogService;

    @GetMapping("/list")
    public TableDataInfo list(QuartzJobLog quartzJobLog) {
        startPage();
        List<QuartzJobLog> list = quartzJobLogService.selectQuartzJobLogList(quartzJobLog);
        return getDataTable(list);
    }
}
