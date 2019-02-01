package com.dimple.modules.FrontDeskModule.controller;

import com.dimple.framework.log.annotation.VLog;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.BlogManager.bean.Blog;
import com.dimple.modules.BackStageModule.BlogManager.service.BlogService;
import com.dimple.modules.BackStageModule.BlogManager.service.CategoryService;
import com.dimple.modules.CommonModule.service.SettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: BlogInfoController
 * @description:
 * @auther: Owenb
 * @date: 01/23/19 17:31
 * @version: 1.0
 */
@Controller("frontBlogInfoController")
public class BlogInfoController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SettingService settingService;


    @ApiOperation("获取博客信息")
    @VLog(title = "博客")
    @GetMapping({"/view/{id}.html", "/view/{id}"})
    public String viewBlogPage(@PathVariable Integer id, Model model) {
        Blog blog = blogService.getBlogAndInfoById(id);
        model.addAttribute("blog", blog);
        if (blog != null && blog.getCategoryId() != null) {
            //填入Category的名称
            model.addAttribute("category", categoryService.getCategoryById(blog.getCategoryId()));
        }
        //获取点击排行(至多列举8条)
        model.addAttribute("clickBlog", blogService.getClickRanking());
        //获取上下文章
        model.addAttribute("navigation", blogService.getNextAndPreviousBlogById(id));
        //获取相关文章（列举至多五条）
        model.addAttribute("relatedBlog", blogService.getRelatedBlogByCategoryId(blog.getCategoryId()));
        //设置版权声明
        model.addAttribute("signature", settingService.getSignatureByKey("blogSignature").getSignatureValue());
        return "front/info";
    }

    @ApiOperation("获取博客版权信息")
    @GetMapping("/public/api/signature/blog")
    @ResponseBody
    public Result getCopyrightMsg() {
        String blogSignature = settingService.getSignatureByKey("blogSignature").getSignatureValue();
        return ResultUtil.success(blogSignature);
    }

}
