package com.dimple.project.log.visitorLog.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.log.visitorLog.domain.Blacklist;
import com.dimple.project.log.visitorLog.domain.VisitLog;
import com.dimple.project.log.visitorLog.service.BlacklistService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: BlacklistController
 * @description:
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
@Controller
@RequestMapping("/log/blacklist")
public class BlacklistController extends BaseController {
    @Autowired
    BlacklistService blacklistService;

    @GetMapping
    @RequiresPermissions("log:blacklist:view")
    public String blacklist() {
        return "log/blacklist/blacklist";
    }

    @RequiresPermissions("log:blacklist:list")
    @GetMapping("/list")
    public TableDataInfo list(Blacklist blacklist) {
        startPage();
        List<VisitLog> list = blacklistService.selectBlacklistList(blacklist);
        return getDataTable(list);
    }

    @RequiresPermissions("log:blacklist:remove")
    @Log(title = "黑名单", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(blacklistService.deleteBlacklistByIds(ids));
    }

    @RequiresPermissions("log:blacklist:detail")
    @GetMapping("/detail/{blacklistId}")
    public String detail(@PathVariable Integer blacklistId, Model model) {
        model.addAttribute("operLog", blacklistService.selectBlacklistById(blacklistId));
        return "log/visitLog/detail";
    }

    @Log(title = "黑名单", businessType = BusinessType.CLEAN)
    @RequiresPermissions("log:blacklist:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        blacklistService.cleanBlacklist();
        return success();
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Blacklist blacklist) {
        return toAjax(blacklistService.insertBlacklist(blacklist));
    }

    @PostMapping("/addBlacklist")
    @ResponseBody
    public AjaxResult addBlacklistSave(String ipAddr) {
        return toAjax(blacklistService.insertBlacklist(ipAddr));
    }
}
