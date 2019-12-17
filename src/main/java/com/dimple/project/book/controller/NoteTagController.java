package com.dimple.project.book.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.book.entity.NoteTag;
import com.dimple.project.book.service.NoteTagService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    NoteTagService noteTagService;

    @PreAuthorize("@permissionService.hasPermission('book:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteTag noteTag) {
        startPage();
        List<NoteTag> list = noteTagService.selectNoteTagList(noteTag);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody NoteTag noteTag) {
        noteTag.setCreateBy(SecurityUtils.getUsername());
        return toAjax(noteTagService.insertNoteTag(noteTag));
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(noteTagService.selectNoteTagById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody NoteTag tag) {
        return toAjax(noteTagService.updateNoteTag(tag));
    }

    @PreAuthorize("@permissionService.hasPermission('book:tag:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(noteTagService.deleteNoteTagByIds(ids));
    }
}
