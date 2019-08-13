package com.dimple.project.tool.tool.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.tool.tool.domain.Tool;
import com.dimple.project.tool.tool.service.ToolService;
import com.dimple.project.tool.toolCategory.domain.ToolCategory;
import com.dimple.project.tool.toolCategory.service.ToolCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: BgToolController
 * @description: 常用工具(Tool)$desc Controller层
 * @auther: Dimple
 * @date: 09/04/2019
 * @version: 1.1
 */
@RestController
@RequestMapping("/tool/tool/")
public class ToolController extends BaseController {
    @Autowired
    ToolService toolService;

    @Autowired
    ToolCategoryService toolCategoryService;

    @RequiresPermissions("tool:tool:view")
    @GetMapping()
    public String tool() {
        return "tool/tool/tool";
    }

    @GetMapping("/list")
    @RequiresPermissions("blog:blog:list")
    @ResponseBody
    public TableDataInfo list(Tool tool) {
        startPage();
        List<Tool> list = toolService.selectToolList(tool);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("toolCategories", toolCategoryService.selectToolCategoryList(new ToolCategory()));
        return "tool/tool/add";
    }

    @Log(title = "系统工具", businessType = BusinessType.INSERT)
    @RequiresPermissions("tool:tool:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tool tool) {
        return toAjax(toolService.insertTool(tool));
    }

    @GetMapping("/edit/{toolId}")
    public String edit(@PathVariable Integer toolId, Model model) {
        model.addAttribute("tool", toolService.selectToolById(toolId));
        model.addAttribute("toolCategories", toolCategoryService.selectToolCategoryList(new ToolCategory()));
        return "tool/tool/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("tool:tool:edit")
    @Log(title = "系统工具", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(Tool tool) {
        return toAjax(toolService.updateTool(tool));
    }

    @DeleteMapping("/remove")
    @Log(title = "系统工具", businessType = BusinessType.DELETE)
    @RequiresPermissions("blog:blog:remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(toolService.deleteToolByIds(ids));
    }


}