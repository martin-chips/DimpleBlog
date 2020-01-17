package com.dimple.project.blog.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Comment;
import com.dimple.project.blog.service.CommentService;
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
 * @className: CommentController
 * @description:
 * @auther: Dimple
 * @date: 2019/10/26
 */
@RestController
@RequestMapping("/blog/comment")
public class CommentController extends BaseController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Comment comment) {
        startPage();
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(commentService.selectCommentById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:add')")
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Validated Comment comment) {
        comment.setCreateBy(SecurityUtils.getUsername());
        return toAjax(commentService.insertComment(comment));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:edit')")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Validated Comment comment) {
        comment.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(commentService.updateComment(comment));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:comment:edit')")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping("/{id}/display/{display}")
    public AjaxResult edit(@PathVariable Long id, @PathVariable Boolean display) {
        Comment comment = new Comment();
        comment.setDisplay(display);
        comment.setId(id);
        comment.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(commentService.updateComment(comment));
    }

    @PreAuthorize("@permissionService.hasPermission('system:config:remove')")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(commentService.deleteCommentByIds(ids));
    }
}
