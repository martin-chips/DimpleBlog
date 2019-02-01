package com.dimple.modules.BackStageModule.BlogManager.controller;

import com.dimple.framework.enums.BlogStatus;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.BlogManager.bean.Blog;
import com.dimple.modules.BackStageModule.BlogManager.service.BlogService;
import com.dimple.modules.BackStageModule.BlogManager.service.CategoryService;
import com.dimple.utils.BaiduPushUtil;
import com.dimple.utils.FileOperateUtil;
import com.dimple.utils.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * 百度站长push平台的url
     */
    @Value("${dimple.baidu.pushUrl}")
    String baiduPushUrl;

    @Autowired
    BlogService blogService;

    @Autowired
    FileOperateUtil fileOperateUtil;

    @Autowired
    CategoryService categoryService;


    @ApiOperation("博客编辑界面")
    @GetMapping("/page/editBlog.html")
    @ApiIgnore
    @RequiresPermissions("page:editBlog:view")
    public String returnPage(Model model) {
        //添加对应在已发布、草稿箱、垃圾箱的数量
        model.addAttribute("count", blogService.selectCountOfBlogStatus());
        model.addAttribute("categories", categoryService.getAllBlogCategory());
        return "blog/editBlog";
    }

    @ApiOperation("博客列表")
    @GetMapping("/page/blogList.html")
    @RequiresPermissions("page:blogList:view")
    @ApiIgnore
    public String blogListPage(Model model) {
        model.addAttribute("published", blogService.selectBlogCountByStatus(BlogStatus.PUBLISHED));
        model.addAttribute("draft", blogService.selectBlogCountByStatus(BlogStatus.DRAFT));
        model.addAttribute("dustbin", blogService.selectBlogCountByStatus(BlogStatus.DUSTBIN));
        model.addAttribute("all", blogService.selectBlogCountByStatus(BlogStatus.ALL));
        return "blog/list/blog";
    }


    @ApiOperation("更新博客")
    @GetMapping("/page/blogList/{id}.html")
    @RequiresPermissions("blogManager:page:blogList:update:view")
    @ApiIgnore
    public String blogUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("blog", blogService.selectBlogByIdBlobs(id));
        return "blog/list/update";
    }


    @ApiOperation("删除博客")
    @DeleteMapping("/api/blog/{id}")
    @RequiresPermissions("blogManager:blog:delete")
    @Log(title = "博客管理", operateType = OperateType.DELETE)
    @ResponseBody
    public Result deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResultUtil.success();
    }

    @ApiOperation("更新博客")
    @Log(title = "博客管理", operateType = OperateType.UPDATE)
    @PutMapping("/api/blog")
    @RequiresPermissions("blogManager:blog:update")
    @ResponseBody
    public Result updateBlog(Blog blog) {
        Blog updateBlog = blogService.updateBlog(blog);
        return ResultUtil.success(updateBlog);
    }

    @ApiOperation("设置博客是否推荐")
    @PutMapping("/api/blog/support/{id}/{status}")
    @RequiresPermissions("blogManager:blog:support")
    @Log(title = "博客管理", operateType = OperateType.CHANGE_STATUS)
    @ResponseBody
    public Result supportBlog(@PathVariable(value = "id") Integer[] ids, @PathVariable Boolean status) {
        int i = blogService.supportBlog(ids, status);
        return ResultUtil.success(i);
    }

    @ApiOperation("设置博客状态为已发布、垃圾箱、草稿箱")
    @Log(title = "博客管理", operateType = OperateType.CHANGE_STATUS)
    @PutMapping("/api/blog/status/{id}/{status}")
    @RequiresPermissions("blogManager:blog:status")
    @ResponseBody
    public Result changeStatus(@PathVariable(value = "id") Integer[] ids, @PathVariable Integer status) {
        int i = blogService.changeBlogStatus(ids, status);
        return ResultUtil.success();
    }

    @ApiOperation("新增博客")
    @PostMapping("/api/blog")
    @RequiresPermissions("blogManager:blog:insert")
    @Log(title = "博客管理", operateType = OperateType.INSERT)
    @ResponseBody
    public Result insertBlog(Blog blog) {
        Blog insertBlog = blogService.insertBlog(blog);
        return ResultUtil.success(insertBlog);
    }

    @ApiOperation("博客的列表信息获取")
    @GetMapping("/api/blog")
    @RequiresPermissions("blogManager:blog:query")
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
    @RequiresPermissions("blogManager:blog:count")
    @ResponseBody
    public Result getBlogStatusCount() {
        Map<String, Integer> map = blogService.selectCountOfBlogStatus();
        return ResultUtil.success(map);
    }

    @ApiOperation("将博客推荐到百度站长平台")
    @PutMapping("/api/blog/baidu/{id}")
    @RequiresPermissions("blogManager:blog:push")
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
