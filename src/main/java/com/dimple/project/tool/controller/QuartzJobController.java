package com.dimple.project.tool.controller;

import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.tool.domain.QuartzJob;
import com.dimple.project.tool.service.QuartzJonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: QuartzJobController
 * @description:
 * @author: Dimple
 * @date: 11/07/19
 */
@RestController
@RequestMapping("/tool/quartz")
public class QuartzJobController extends BaseController {

    @Autowired
    QuartzJonService quartzJonService;

    @GetMapping("/list")
    public TableDataInfo list(QuartzJob quartzJob) {
        startPage();
        List<QuartzJob> quartzJobList = quartzJonService.selectQuartzJobList(quartzJob);
        return getDataTable(quartzJobList);
    }

    @PostMapping()
    public AjaxResult add(@RequestBody QuartzJob quartzJob) {
        return toAjax(quartzJonService.insertQuartzJob(quartzJob));
    }

    @GetMapping("/{id}")
    public AjaxResult add(@PathVariable Long id) {
        return AjaxResult.success(quartzJonService.selectQuartzJobById(id));
    }

    @PutMapping()
    public AjaxResult edit(@RequestBody QuartzJob quartzJob) {
        return toAjax(quartzJonService.updateQuartzJob(quartzJob));
    }

    @DeleteMapping("{id}")
    public AjaxResult delete(@PathVariable Long id) {
        return toAjax(quartzJonService.deleteQuartzJob(id));
    }

    @PutMapping("/exe/{id}")
    public AjaxResult execute(@PathVariable Long id) {
        quartzJonService.executeQuartzJobById(id);
        return AjaxResult.success();
    }

    @PutMapping("/status/{id}")
    public AjaxResult updateQuartzJobStatus(@PathVariable Long id) {
        return toAjax(quartzJonService.updateQuartzJobStatus(id));
    }

}

