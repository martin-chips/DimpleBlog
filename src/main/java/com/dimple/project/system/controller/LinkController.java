package com.dimple.project.system.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.domain.BaseEntity;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.system.domain.Link;
import com.dimple.project.system.service.LinkService;
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
 * @className: LinkController
 * @description: 友链管理Controller
 * @author: Dimple
 * @date: 2019-10-29
 */
@RestController
@RequestMapping("/system/link")
public class LinkController extends BaseController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:list')")
    @GetMapping("/list")
    public TableDataInfo list(Link link) {
        startPage();
        List<Link> list = linkService.selectLinkList(link);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(linkService.selectLinkById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:add')")
    @Log(title = "友链管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated Link link) {
        link.setCreateBy(SecurityUtils.getUsername());
        return toAjax(linkService.insertLink(link));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:edit')")
    @Log(title = "友链管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated(BaseEntity.Update.class) Link link) {
        link.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(linkService.updateLink(link));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:edit')")
    @Log(title = "友链管理", businessType = BusinessType.UPDATE)
    @PutMapping("/pass/{id}/{pass}")
    public AjaxResult handlePass(@PathVariable Long id, @PathVariable Boolean pass) {
        return toAjax(linkService.handleLinkPass(id, pass));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:link:remove')")
    @Log(title = "友链管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult delete(@PathVariable String ids) {
        return toAjax(linkService.deleteLinkByIds(ids));
    }


}
