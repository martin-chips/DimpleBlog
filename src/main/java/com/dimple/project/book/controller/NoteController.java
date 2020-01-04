package com.dimple.project.book.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.book.domain.Note;
import com.dimple.project.book.service.NoteService;
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
 * @className: NoteController
 * @description: 读书笔记Controller
 * @author: Dimple
 * @date: 2019-11-27
 */
@RestController
@RequestMapping("book/note")
public class NoteController extends BaseController {
    final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:list')")
    @GetMapping("/list")
    public TableDataInfo list(Note note) {
        startPage();
        List<Note> list = noteService.selectNoteList(note);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:add')")
    @Log(title = "笔记管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody Note note) {
        return toAjax(noteService.insertNote(note));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:add')")
    @Log(title = "笔记管理", businessType = BusinessType.INSERT)
    @PostMapping("draft")
    public AjaxResult draft(@RequestBody Note note) {
        return toAjax(noteService.insertNote(note));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:edit')")
    @Log(title = "笔记管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody Note note) {
        return toAjax(noteService.updateNote(note));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:edit')")
    @Log(title = "笔记管理", businessType = BusinessType.UPDATE)
    @PutMapping("draft")
    public AjaxResult editDraft(@RequestBody Note note) {
        return toAjax(noteService.updateNote(note));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:edit')")
    @Log(title = "笔记管理", businessType = BusinessType.UPDATE)
    @PutMapping("support/{id}/{support}")
    public AjaxResult editSupport(@PathVariable Long id, @PathVariable Boolean support) {
        Note note = new Note();
        note.setId(id);
        note.setSupport(support);
        return toAjax(noteService.updateNote(note));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:edit')")
    @Log(title = "笔记管理", businessType = BusinessType.UPDATE)
    @PutMapping("comment/{id}/{comment}")
    public AjaxResult editComment(@PathVariable Long id, @PathVariable Boolean comment) {
        Note note = new Note();
        note.setId(id);
        note.setComment(comment);
        return toAjax(noteService.updateNote(note));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(noteService.selectNoteById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('book:note:remove')")
    @Log(title = "笔记管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(noteService.deleteNoteByIds(ids));
    }


    @PreAuthorize("@permissionService.hasPermission('book:note:edit')")
    @GetMapping("tag/{query}")
    public TableDataInfo getCommonTag(@PathVariable String query) {
        return getDataTable(noteService.selectNoteTagList(query));
    }

}
