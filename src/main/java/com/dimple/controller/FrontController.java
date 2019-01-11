package com.dimple.controller;

import com.dimple.bean.Blog;
import com.dimple.framework.log.annotation.VLog;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.service.FrontService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: FrontController
 * @Description: 前端请求的Controller
 * @Auther: Owenb
 * @Date: 12/12/18 17:00
 * @Version: 1.0
 */
@Controller
@Api("前台请求处理Controller")
public class FrontController {


    @Autowired
    FrontService frontService;

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", frontService.selectCategoryNameToDisplay());
        model.addAttribute("blogs", frontService.getBlogsInfo());
        model.addAttribute("peopleSee", frontService.getBlogsPeopleSee());
        model.addAttribute("clickBlog", frontService.getClickBlog());
        model.addAttribute("supportBlog", frontService.getSupportBlog());
        model.addAttribute("newestUpdateBlog", frontService.getNewestUpdateBlog());
        model.addAttribute("rotations", frontService.getRotationByPlace(1));
        model.addAttribute("rotationLines", frontService.getRotationByPlace(2));
        return "front/index";
    }


    @GetMapping("/leaveComment.html")
    public String leaveCommentPage() {
        return "front/leaveComment";
    }

    @GetMapping("/about.html")
    public String aboutPage() {
        return "front/about";
    }

    @GetMapping("/technology.html")
    public String technologyPage(Model model) {
        model.addAttribute("clickBlog", frontService.getClickBlog());
        model.addAttribute("supportBlog", frontService.getSupportBlog());
        model.addAttribute("newestUpdateBlog", frontService.getNewestUpdateBlog());

        return "front/technology";
    }

    @GetMapping("/free.html")
    public String freePage(Model model) {
        model.addAttribute("clickBlog", frontService.getClickBlog());
        model.addAttribute("supportBlog", frontService.getSupportBlog());
        model.addAttribute("newestUpdateBlog", frontService.getNewestUpdateBlog());

        return "front/free";
    }

    @GetMapping("/software.html")
    public String softwarePage(Model model) {
        model.addAttribute("clickBlog", frontService.getClickBlog());
        model.addAttribute("supportBlog", frontService.getSupportBlog());
        model.addAttribute("newestUpdateBlog", frontService.getNewestUpdateBlog());

        return "front/software";
    }

    @GetMapping("/resource.html")
    public String sourcePage(Model model) {
        model.addAttribute("clickBlog", frontService.getClickBlog());
        model.addAttribute("supportBlog", frontService.getSupportBlog());
        model.addAttribute("newestUpdateBlog", frontService.getNewestUpdateBlog());
        return "front/resource";
    }


    /**
     * 获取个人名片信息
     * todo 做一个网站资源管理
     *
     * @return
     */
    @GetMapping("/front/businessCard")
    @ResponseBody
    public Result getBusinessCard() {
        return null;
    }


    @GetMapping("/public/api/front/newestBlog")
    @ResponseBody
    public Result getNewestBlog(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        List<Map<String, Object>> blogs = frontService.getNewestBlog(pageable);
        return ResultUtil.success(blogs);
    }

    @ApiOperation("获取博客信息")
    @VLog(title = "博客")
    @GetMapping({"/view/{id}.html", "/view/{id}"})
    public String viewBlogPage(@PathVariable Integer id, Model model) {
        Blog blog = frontService.getBlogInfo(id);
        model.addAttribute("blog", blog);
        if (blog != null && blog.getCategoryId() != null) {
            model.addAttribute("category", frontService.getCategoryInfoByCategoryId(blog.getCategoryId()));
        }
        model.addAttribute("clickBlog", frontService.getClickBlog());
        model.addAttribute("supportBlog", frontService.getSupportBlog());
        model.addAttribute("navigation", frontService.getBlogOtherInfo(id));
        return "front/info";
    }

    @ApiOperation("获取点击排行的博客信息")
    @GetMapping("/public/api/front/clickBlog")
    @ResponseBody
    public Result getClickBlog() {
        List<Blog> clickBlog = frontService.getClickBlog();
        return ResultUtil.success(clickBlog);
    }

    @ApiOperation("获取版权信息")
    @GetMapping("/public/api/signature/blog")
    @ResponseBody
    public Result getCopyrightMsg() {
        String blogSignature = frontService.getSignatureByKey("blogSignature");
        return ResultUtil.success(blogSignature);
    }

}
