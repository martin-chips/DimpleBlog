package com.dimple.project.blog.comment.controller;

import com.dimple.common.utils.poi.ExcelUtil;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.comment.domain.Comment;
import com.dimple.project.blog.comment.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: CommentController
 * @description: 留言Controller
 * @auther: Dimple
 * @date: 2019/7/17
 * @version: 1.0
 */
@Controller
@RequestMapping("/blog/comment")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @RequiresPermissions("blog:comment:view")
    @GetMapping()
    public String comment() {
        return "blog/comment/comment";
    }

    /**
     * 查询留言列表
     */
    @RequiresPermissions("blog:comment:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Comment bgComment) {
        startPage();
        List<Comment> list = commentService.selectCommentList(bgComment);
        return getDataTable(list);
    }

    /**
     * 导出留言列表
     */
    @RequiresPermissions("blog:comment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Comment bgComment) {
        List<Comment> list = commentService.selectCommentList(bgComment);
        ExcelUtil<Comment> util = new ExcelUtil<>(Comment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 删除留言
     */
    @RequiresPermissions("blog:comment:remove")
    @Log(title = "留言", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(commentService.deleteBgCommentByIds(ids));
    }
}
