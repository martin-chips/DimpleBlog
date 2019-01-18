package com.dimple.controller.blog;

import com.dimple.bean.Blog;
import com.dimple.framework.enums.BlogStatus;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.service.BlogService;
import com.dimple.service.CategoryService;
import com.dimple.utils.BaiduPushUtil;
import com.dimple.utils.FileOperateUtil;
import com.dimple.utils.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Map;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : BlogController
 * @description :
 * @date: 11/20/18 14:27
 */
@Controller
@Api("博客管理接口测试模块")
@Slf4j
public class BlogController {

    @Value("${dimple.baidu.pushUrl}")
    String baiduPushUrl;

    @Autowired
    BlogService blogService;
    @Autowired
    FileOperateUtil fileOperateUtil;
    @Autowired
    CategoryService categoryService;


    @GetMapping("/page/editBlog.html")
    @ApiIgnore
    public String returnPage(Model model) {
        //添加对应在已发布、草稿箱、垃圾箱的数量
        model.addAttribute("count", blogService.selectCountOfBlogStatus());
        model.addAttribute("categories", categoryService.getAllBlogCategory());
        return "blog/editBlog";
    }

    @GetMapping("/page/blogList.html")
    @ApiIgnore
    public String blogListPage(Model model) {
        model.addAttribute("published", blogService.selectBlogCountByStatus(BlogStatus.PUBLISHED));
        model.addAttribute("draft", blogService.selectBlogCountByStatus(BlogStatus.DRAFT));
        model.addAttribute("dustbin", blogService.selectBlogCountByStatus(BlogStatus.DUSTBIN));
        model.addAttribute("all", blogService.selectBlogCountByStatus(BlogStatus.ALL));
        return "blog/list/blog";
    }


    @GetMapping("/page/blogList/{id}.html")
    @ApiIgnore
    public String blogUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("blog", blogService.selectBlogByIdBlobs(id));
        return "blog/list/update";
    }


    @ApiOperation("删除博客")
    @DeleteMapping("/api/blog/{id}")
    @Log(title = "博客管理", operateType = OperateType.DELETE)
    @ResponseBody
    public Result deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResultUtil.success();
    }

    @ApiOperation("更新博客")
    @Log(title = "博客管理", operateType = OperateType.UPDATE)
    @PutMapping("/api/blog")
    @ResponseBody
    public Result updateBlog(Blog blog) {
        Blog updateBlog = blogService.updateBlog(blog);
        return ResultUtil.success(updateBlog);
    }

    @ApiOperation("设置博客是否推荐")
    @PutMapping("/api/blog/support/{id}/{status}")
    @Log(title = "博客管理", operateType = OperateType.CHANGE_STATUS)
    @ResponseBody
    public Result supportBlog(@PathVariable(value = "id") Integer[] ids, @PathVariable Boolean status) {
        int i = blogService.supportBlog(ids, status);
        return ResultUtil.success(i);
    }

    @ApiOperation("设置博客状态为已发布、垃圾箱、草稿箱")
    @Log(title = "博客管理", operateType = OperateType.CHANGE_STATUS)
    @PutMapping("/api/blog/status/{id}/{status}")
    @ResponseBody
    public Result changeStatus(@PathVariable(value = "id") Integer[] ids, @PathVariable Integer status) {
        int i = blogService.changeBlogStatus(ids, status);
        return ResultUtil.success();
    }

    @ApiOperation("新增博客")
    @PostMapping("/api/blog")
    @Log(title = "博客管理", operateType = OperateType.INSERT)
    @ResponseBody
    public Result insertBlog(Blog blog) {
        Blog insertBlog = blogService.insertBlog(blog);
        return ResultUtil.success(insertBlog);
    }

    @ApiOperation("博客的列表信息获取")
    @GetMapping("/api/blog")
    @Log(title = "博客管理", operateType = OperateType.SELECT)
    @ResponseBody
    public Result getAllBlog(
            @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "status", required = false) Integer status,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Blog> allBlogs = blogService.getAllBlogs(title, startTime, endTime, status, pageable);
        return ResultUtil.success(allBlogs);
    }


    @ApiOperation("获取博客状态的分类信息")
    @GetMapping("/api/blog/count")
    @Log(title = "博客管理", operateType = OperateType.SELECT)
    @ResponseBody
    public Result getBlogStatusCount() {
        Map<String, Integer> map = blogService.selectCountOfBlogStatus();
        return ResultUtil.success(map);
    }

    @PutMapping("/api/blog/baidu/{id}")
    @ResponseBody
    public Result pushBaidu(@PathVariable("id") Integer ids[]) {
        if (ids == null || ids.length == 0) {
            return ResultUtil.error(1, "数据异常，请稍后重试！");
        }
        String params[] = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            params[i] = IpUtil.getHostIp() + "/view/" + ids[i];
        }
        String post = BaiduPushUtil.Post(baiduPushUrl, params);
        return ResultUtil.success(post);
    }
}
