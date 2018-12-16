package com.dimple.controller;

import com.alibaba.fastjson.JSON;
import com.dimple.bean.Blog;
import com.dimple.service.BlogService;
import com.dimple.service.CategoryService;
import com.dimple.utils.FileOperateUtil;
import com.dimple.utils.QiNiuUtils;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.util.StringMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: BlogController
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 14:27
 * @Version: 1.0
 */
@Controller
@Api("博客管理接口测试模块")
@Slf4j
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    FileOperateUtil fileOperateUtil;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/page/editBlog.html")
    public String returnPage(Model model) {
        //添加对应在已发布、草稿箱、垃圾箱的数量
        model.addAttribute("count", blogService.selectCountOfBlogStatus());
        model.addAttribute("categories", categoryService.getAllBlogCategory());
        return "blog/editBlog";
    }

    @GetMapping("/page/blogList.html")
    public String blogListPage(Model model) {
        return "blog/list/blog";
    }

    @GetMapping("/page/blogList/{id}.html")
    public String blogUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("blog", blogService.selectBlogById(id));
        return "blog/list/update";
    }



    @ApiOperation("删除博客")
    @DeleteMapping("/api/blog/{id}")
    @ResponseBody
    public Result deleteBlog(@PathVariable Integer id) {
        int i = blogService.deleteBlog(id);
        return ResultUtil.success(i);
    }

    @ApiOperation("更新博客的状态")
    @DeleteMapping("/api/blog/{status}/{id}")
    @ResponseBody
    public Result changeBlogStatus(@PathVariable Integer id, @PathVariable Integer status) {
        int i = blogService.changeBlogStatus(id, status);
        return ResultUtil.success(i);
    }

    @ApiOperation("更新博客")
    @PutMapping("/api/blog")
    @ResponseBody
    public Result updateBlog(Blog blog) {
        int i = blogService.updateBlog(blog);
        return ResultUtil.success(i);
    }

    @ApiOperation("新增博客")
    @PostMapping("/api/blog")
    @ResponseBody
    public Result insertBlog(Blog blog) {
        int i = blogService.insertBlog(blog);
        return ResultUtil.success(i);
    }

    @ApiOperation("获取博客的列表信息")
    @GetMapping("/api/blog")
    @ResponseBody
    public Result getAllBlog(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "status", required = false) Integer status,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime) {
        PageHelper.startPage(pageNum, pageSize, "create_time desc");
        List<Blog> blogs = (List<Blog>) blogService.selectAllBlog(title, startTime, endTime, status);
        PageInfo pageInfo = new PageInfo(blogs);
        return ResultUtil.success(pageInfo);
    }


    @PostMapping(value = "/uploadQiNiu")
    @ResponseBody
    public Object uploadQiNiu(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        File distFile = null;
        if (!file.isEmpty()) {
            try {
                String path = request.getSession().getServletContext().getRealPath("/upload");
                String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                distFile = new File(path + "/" + fileName);
                file.transferTo(distFile);
                StringMap stringMap = QiNiuUtils.upload(fileName, path + "/");
                return stringMap.map();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                distFile.delete();
            }
        }
        return null;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String content(HttpServletRequest request) {
        //创建map集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 转换成多部分request
            MultipartHttpServletRequest multipartRequest = ((MultipartHttpServletRequest) request);
            //获得上传的所有文件名
            Iterator<String> fileNameIter = multipartRequest.getFileNames();
            //进行循环遍历
            while (fileNameIter.hasNext()) {
                //根据文件名获取文件
                MultipartFile file = multipartRequest.getFile(fileNameIter.next());
                //若文件不为null
                if (file != null) {
                    //获取上传时的文件名
                    String myFilename = file.getOriginalFilename();
                    System.out.println("上传时的文件名:" + myFilename);
                    //去除空格
                    if (myFilename.trim() != "") {
                        //获得文件名
                        String filename = file.getOriginalFilename();
                        //截取文件名
                        String fileBaseName = filename.substring(0, filename.lastIndexOf("."));
                        //截取文件后缀
                        String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
                        //时间格式化对象
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                        //生成时间戳
                        String newFilename = simpleDateFormat.format(new Date());
                        //生成新的文件名
                        String filenames = newFilename + new Random().nextInt(1000) + "." + fileExt;
                        //获得保存文件路径
                        String filePath = request.getSession().getServletContext().
                                getRealPath("/") + "\\upload\\" + filenames;
                        //部署保存路径
                        //String filePath=imageHtmlLocation+filenames;
                        System.out.println("保存的路径:" + filePath);
                        //保存文件
                        File targetFile = new File(filePath);
                        if (!targetFile.exists()) {
                            //先得到文件的上级目录，并创建上级目录，在创建文件
                            targetFile.getParentFile().mkdir();
                            try {
                                //创建文件
                                targetFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //上传文件
                        file.transferTo(targetFile);
                        //获得工程的相对路径
                        String saveUrl = "http://127.0.0.1:8060/upload/" + filenames;
                        //部署访问路径
                        //String saveUrl =fileuploadPrefix+imageHtmlUrl+filenames;
                        System.out.println("相对路径:" + saveUrl);

                        //将文件保存的相对路径返回页面
                        map.put("path", "http://p2sj58chj.bkt.clouddn.com/blog/QQ%E6%88%AA%E5%9B%BE20180814175737.png");
                    }
                }
            }
            //保存添加信息
            request.setAttribute("msg", "true");
            return JSON.toJSONString("http://p2sj58chj.bkt.clouddn.com/blog/QQ%E6%88%AA%E5%9B%BE20180814175737.png");
        } catch (Exception e) {
            e.printStackTrace();
            //保存失败
            request.setAttribute("msg", "false");
            return null;
        }
    }



    @ApiOperation("获取博客状态的分类信息")
    @GetMapping("/api/blog/count")
    @ResponseBody
    public Result getBlogStatusCount() {
        Map<String, Integer> map = blogService.selectCountOfBlogStatus();
        return ResultUtil.success(map);
    }

}
