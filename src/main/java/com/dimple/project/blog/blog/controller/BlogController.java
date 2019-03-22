package com.dimple.project.blog.blog.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.blog.domain.Blog;
import com.dimple.project.blog.blog.service.BlogService;
import com.dimple.project.blog.category.domain.Category;
import com.dimple.project.blog.category.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: BlogController
 * @description: 博客处理Controller
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/blog/blog")
public class BlogController extends BaseController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @RequiresPermissions("blog:blog:view")
    @GetMapping()
    public String blog() {
        return "blog/blog/blog";
    }

    @GetMapping("/list")
    @RequiresPermissions("blog:blog:list")
    @ResponseBody
    public TableDataInfo list(Blog blog) {
        startPage();
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("categories", categoryService.selectCategoryList(new Category()));
        return "blog/blog/add";
    }

    @Log(title = "博客管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("blog:blog:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Blog blog) {
        int i = blogService.insertBlog(blog);
        return toAjax(i);
    }

    @GetMapping("/edit/{blogId}")
    public String edit(@PathVariable Integer blogId, Model model) {
        model.addAttribute("blog", blogService.selectBlogById(blogId));
        model.addAttribute("categories", categoryService.selectCategoryList(null));
        return "/blog/blog/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("blog:blog:edit")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PutMapping("/support/{support}")
    @RequiresPermissions("blog:blog:support")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult supportSave(String blogIds, @PathVariable String support) {
        return toAjax(blogService.updateBlogSupportById(blogIds, support));
    }

    @PutMapping("/status/{status}")
    @RequiresPermissions("blog:blog:support")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult statusSave(String blogIds, @PathVariable String status) {
        return toAjax(blogService.updateBlogStatusById(blogIds, status));
    }

    @DeleteMapping("/remove")
    @Log(title = "博客管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("blog:blog:remove")
    @ResponseBody
    public AjaxResult remove(Integer ids[]) {
        return toAjax(blogService.deleteBlogById(ids));
    }


}
