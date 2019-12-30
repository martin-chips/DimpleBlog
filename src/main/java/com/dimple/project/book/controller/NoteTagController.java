package com.dimple.project.book.controller;

import com.dimple.common.enums.TagType;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.service.TagService;
import com.dimple.project.common.domain.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @className: NoteTagController
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
@RestController
@RequestMapping("book/tag")
public class NoteTagController extends BaseController {

    final TagService tagService;

    public NoteTagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tag tag) {
        startPage();
        tag.setType(TagType.NOTE.getType());
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody Tag tag) {
        tag.setCreateBy(SecurityUtils.getUsername());
        tag.setType(TagType.NOTE.getType());
        return toAjax(tagService.insertTag(tag));
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(tagService.selectTagById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody Tag tag) {
        return toAjax(tagService.updateTag(tag));
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(tagService.deleteTagByIds(ids));
    }
}
