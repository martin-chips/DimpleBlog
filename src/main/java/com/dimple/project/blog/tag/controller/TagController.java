package com.dimple.project.blog.tag.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.tag.domain.Tag;
import com.dimple.project.blog.tag.service.TagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: TagController
 * @description: 标签操作Controller
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@RequestMapping("/blog/tag")
@Controller
public class TagController extends BaseController {

    @Autowired
    TagService tagService;

    @RequiresPermissions("blog:tag:view")
    @GetMapping()
    public String tag() {
        return "blog/tag/tag";
    }

    @RequiresPermissions("blog:tag:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Tag tag) {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add() {
        return "blog/tag/add";
    }

    @RequiresPermissions("blog:tag:add")
    @Log(title = "博客标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tag tag) {
        return toAjax(tagService.insertTag(tag));
    }

    @GetMapping("/edit/{tagId}")
    public String edit(@PathVariable Integer tagId, Model model) {
        model.addAttribute("tag", tagService.selectTagById(tagId));
        return "blog/tag/edit";
    }


    @RequiresPermissions("blog:tag:edit")
    @Log(title = "博客标签", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Tag tag) {
        return toAjax(tagService.updateTag(tag));
    }

    @RequiresPermissions("blog:tag:remove")
    @Log(title = "博客标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(tagService.deleteTagByIds(ids));
    }


    @PostMapping("/checkTagTitleUnique")
    @ResponseBody
    public String checkCategoryTitleUnique(String title) {
        return tagService.checkTagTitleUnique(title);
    }
}
