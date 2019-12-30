package com.dimple.project.blog.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.service.BlogService;
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
 * @className: BlogController
 * @description: 博客Controller
 * @author: Dimple
 * @date: 2019-10-28
 */
@RestController
@RequestMapping("/blog/blog")
public class BlogController extends BaseController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:list')")
    @GetMapping("/list")
    public TableDataInfo list(Blog blog) {
        startPage();
        List<Blog> list = blogService.selectBlogList(blog);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:add')")
    @Log(title = "博客管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@Validated(Blog.Publish.class) @RequestBody Blog blog) {
        return toAjax(blogService.insertBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:add')")
    @Log(title = "博客管理", businessType = BusinessType.INSERT)
    @PostMapping("draft")
    public AjaxResult draft(@Validated(Blog.Draft.class) @RequestBody Blog blog) {
        return toAjax(blogService.insertBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@Validated(Blog.Publish.class) @RequestBody Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @PutMapping("draft")
    public AjaxResult editDraft(@RequestBody @Validated(Blog.Draft.class) Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @PutMapping("support")
    public AjaxResult editSupport(@RequestBody Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @PutMapping("comment")
    public AjaxResult editComment(@RequestBody Blog blog) {
        return toAjax(blogService.updateBlog(blog));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(blogService.selectBlogById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:remove')")
    @Log(title = "博客管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        return toAjax(blogService.deleteBlogById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:blog:edit')")
    @GetMapping("tag/{query}")
    public TableDataInfo getCommonTag(@PathVariable String query) {
        return getDataTable(blogService.selectBlogTagList(query));
    }

}
