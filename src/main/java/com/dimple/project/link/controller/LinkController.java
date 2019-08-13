package com.dimple.project.link.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.link.domain.Link;
import com.dimple.project.link.service.LinkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: LinkController
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Controller
@RequestMapping("/link/link")
public class LinkController extends BaseController {

    @Autowired
    LinkService linkService;

    @RequiresPermissions("link:link:view")
    @GetMapping()
    public String link(Model model) {
        model.addAttribute("map", linkService.selectLinkCount());
        return "link/link";
    }

    @RequiresPermissions("link:link:list")
    @GetMapping("list")
    @ResponseBody
    public TableDataInfo list(Link link) {
        startPage();
        List<Link> list = linkService.selectLinkList(link);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add() {
        return "link/add";
    }

    @PostMapping("/add")
    @ResponseBody
    @Log(title = "系统友链", businessType = BusinessType.INSERT)
    @RequiresPermissions("link:link:add")
    public AjaxResult addSave(Link link) {
        return toAjax(linkService.insertLink(link));
    }

    @GetMapping("/edit/{linkId}")
    public String edit(@PathVariable Integer linkId, Model model) {
        model.addAttribute("link", linkService.selectLinkById(linkId));
        return "link/edit";
    }

    @PutMapping("/edit")
    @Log(title = "系统友链", businessType = BusinessType.UPDATE)
    @ResponseBody
    @RequiresPermissions("link:link:edit")
    public AjaxResult editSave(Link link) {
        return toAjax(linkService.updateLink(link));
    }

    @RequiresPermissions("link:link:remove")
    @Log(title = "系统友链", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(linkService.deleteLinkByIds(ids));
    }

    @Log(title = "系统友链", businessType = BusinessType.UPDATE)
    @PutMapping("/display/{display}")
    @RequiresPermissions("link:link:display")
    @ResponseBody
    public AjaxResult display(Integer id, @PathVariable Integer display) {
        return toAjax(linkService.changeDisplay(id, display));
    }

    /**
     * 通过友链
     */
    @Log(title = "系统友链", businessType = BusinessType.UPDATE)
    @PutMapping("/processed")
    @RequiresPermissions("link:link:pass")
    @ResponseBody
    public AjaxResult processed(String ids) {
        return toAjax(linkService.processedLinkByIds(ids));
    }

}
