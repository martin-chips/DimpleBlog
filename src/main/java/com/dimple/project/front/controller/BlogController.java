package com.dimple.project.front.controller;

import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.service.BlogService;
import com.dimple.project.front.domain.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: FrontBlogController
 * @description:
 * @author: Dimple
 * @date: 2019/12/15
 */
@RestController("frontBlogController")
@RequestMapping("/f")
public class BlogController extends BaseController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blog")
    @VLog(title = "首页")
    public TableDataInfo blog(BlogQuery blogQuery) {
        startPage();
        List<Blog> blogList = blogService.selectBlogList(blogQuery);
        return getDataTable(blogList);
    }

    @GetMapping("/blog/{id}")
    @VLog(title = "查看博客", pageId = "#id")
    public AjaxResult blogDetail(@PathVariable Long id) {
        return AjaxResult.success(blogService.selectBlogDetailById(id));
    }

    @PutMapping("/blog/like/{id}")
    @VLog(title = "点赞博客")
    public AjaxResult likeBlog(@PathVariable Long id) {
        return AjaxResult.success(blogService.incrementBlogLike(id));
    }
}
