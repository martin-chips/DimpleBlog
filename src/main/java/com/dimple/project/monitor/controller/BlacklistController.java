package com.dimple.project.monitor.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.domain.BaseEntity;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.monitor.domain.Blacklist;
import com.dimple.project.monitor.service.BlacklistService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
 * @className: BlacklistController
 * @description:
 * @author: Dimple
 * @date: 10/24/19
 */
@RestController()
@RequestMapping("monitor/blacklist")
public class BlacklistController extends BaseController {
    final BlacklistService blacklistService;

    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    /**
     * 获取黑名单列表
     */
    @PreAuthorize("@permissionService.hasPermission('monitor:blacklist:list')")
    @GetMapping("/list")
    public TableDataInfo list(Blacklist blacklist) {
        startPage();
        List<Blacklist> list = blacklistService.selectBlacklistList(blacklist);
        return getDataTable(list);
    }

    /**
     * 根据黑名单id获取详细信息
     */
    @PreAuthorize("@permissionService.hasPermission('monitor:blacklist:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(blacklistService.selectBlacklistById(id));
    }

    /**
     * 新增黑名单
     */
    @PreAuthorize("@permissionService.hasPermission('monitor:blacklist:add')")
    @Log(title = "黑名单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Blacklist blacklist) {
        blacklist.setCreateBy(SecurityUtils.getUsername());
        return toAjax(blacklistService.insertBlacklist(blacklist));
    }

    /**
     * 修改黑名单
     */
    @PreAuthorize("@permissionService.hasPermission('monitor:blacklist:edit')")
    @Log(title = "黑名单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated(BaseEntity.Update.class) Blacklist blacklist) {
        blacklist.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(blacklistService.updateBlacklist(blacklist));
    }

    /**
     * 删除黑名单
     */
    @PreAuthorize("@permissionService.hasPermission('monitor:blacklist:remove')")
    @Log(title = "黑名单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(blacklistService.deleteBlacklistByIds(ids));
    }

}
