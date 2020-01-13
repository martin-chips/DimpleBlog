package com.dimple.project.tool.controller;

import com.dimple.common.utils.StringUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.common.domain.QiNiuConfig;
import com.dimple.project.tool.domain.QiNiuContent;
import com.dimple.project.tool.service.QiNiuService;
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
 * @className: QiNiuController
 * @description:
 * @author: Dimple
 * @date: 11/06/19
 */
@RestController
@RequestMapping("/tool/qiNiu")
public class QiNiuController extends BaseController {

    @Autowired
    QiNiuService qiNiuService;

    @GetMapping("/list")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:list')")
    public TableDataInfo list(QiNiuContent qiNiuContent) {
        startPage();
        List<QiNiuContent> qiNiuContentList = qiNiuService.selectContentList(qiNiuContent);
        return getDataTable(qiNiuContentList);
    }

    @GetMapping("/config")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiuConfig:query')")
    public AjaxResult config() {
        QiNiuConfig qiNiuConfig = qiNiuService.getQiNiuConfig();
        if (StringUtils.isNotEmpty(qiNiuConfig.getSecretKey())) {
            //secretKey 打码
            qiNiuConfig.setSecretKey("**************************");
        }
        return AjaxResult.success(qiNiuConfig);
    }

    @PutMapping("/config")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiuConfig:edit')")
    @Log(title = "七牛云配置", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody QiNiuConfig qiNiuConfig) {
        return toAjax(qiNiuService.updateQiNiuConfig(qiNiuConfig));
    }

    @PostMapping
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:upload')")
    @Log(title = "七牛云存储", businessType = BusinessType.UPLOAD)
    public AjaxResult upload(@RequestParam MultipartFile file) {
        QiNiuContent qiNiuContent = qiNiuService.upload(file);
        return AjaxResult.success(qiNiuContent);
    }

    @PreAuthorize("@permissionService.hasPermission('system:qiNiu:synchronize')")
    @Log(title = "七牛云存储", businessType = BusinessType.UPDATE)
    @PostMapping("/synchronize")
    public AjaxResult synchronize() {
        return AjaxResult.success(qiNiuService.synchronize());
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:remove')")
    @Log(title = "七牛云存储", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable String ids) {
        return toAjax(qiNiuService.deleteQiNiuContent(ids));
    }

    @GetMapping("/download/{id}")
    @PreAuthorize("@permissionService.hasPermission('tool:qiNiu:download')")
    @Log(title = "七牛云存储", businessType = BusinessType.DOWNLOAD)
    public AjaxResult download(@PathVariable Long id) {
        return AjaxResult.success(qiNiuService.getDownloadUrl(id));
    }
}
