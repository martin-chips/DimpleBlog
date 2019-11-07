package com.dimple.project.tool.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.tool.domain.LocalStorage;
import com.dimple.project.tool.service.LocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @className: LocalStorageController
 * @description: 本地存储
 * @author: Dimple
 * @date: 11/07/19
 */
@RestController
@RequestMapping("/tool/localStorage")
public class LocalStorageController extends BaseController {
    @Autowired
    LocalStorageService localStorageService;

    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:list')")
    @GetMapping("/list")
    public TableDataInfo list(LocalStorage localStorage) {
        startPage();
        List<LocalStorage> list = localStorageService.selectLocalStorageList(localStorage);
        return getDataTable(list);
    }

    @Log(title = "本地存储", businessType = BusinessType.UPLOAD)
    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:upload')")
    @PostMapping
    public AjaxResult upload(@RequestParam String name, @RequestParam MultipartFile file) {
        return toAjax(localStorageService.upload(name, file));
    }

    @Log(title = "本地存储", businessType = BusinessType.UPDATE)
    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:edit')")
    @PutMapping
    public AjaxResult upload(@RequestBody LocalStorage localStorage) {
        return toAjax(localStorageService.updateLocalStorage(localStorage));
    }


    @Log(title = "本地存储", businessType = BusinessType.DELETE)
    @PreAuthorize("@permissionService.hasPermission('tool:localStorage:remove')")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id) {
        return toAjax(localStorageService.deleteLocalStorage(id));
    }
}
