package com.dimple.controller;

import com.dimple.bean.Archives;
import com.dimple.enums.LinksSearchCode;
import com.dimple.service.ArchivesService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: Archives
 * @Description: 博客类别
 * @Auther: Dimple
 * @Date: 2018/12/11 19:29
 * @Version: 1.0
 */
@Controller
public class ArchivesController {

    @Autowired
    ArchivesService archivesService;

    /**
     * 跳转到归档页面
     *
     * @return
     */
    @GetMapping("/blog/archive.html")
    public String archivesPage() {
        return "blogManager/archives/archives";
    }

    @GetMapping("/blog/archives/{id}.html")
    public String archivesUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("archives", archivesService.selectBlogArchivesByArchivesId(id));
        return "blogManager/archives/update";
    }

    @GetMapping("/blog/archives/add.html")
    public String archivesAddPage() {
        return "blogManager/archives/add";
    }

    /**
     * 获取所有的归档数据
     *
     * @return
     */
    @GetMapping("/blog/archives.json")
    @ResponseBody
    public Result getAllBlogArchives(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "title", required = false) String title) {
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        List<Archives> allBlogArchives = archivesService.getAllBlogArchives(startTime, endTime, description, title);
        PageInfo pageInfo = new PageInfo(allBlogArchives);
        return ResultUtil.success(pageInfo);
    }

    @PostMapping("/blog/archives")
    @ResponseBody
    public Result insertBlogArchives(Archives archives) {
        int i = archivesService.insertBlogArchives(archives);
        return ResultUtil.success(i);
    }

    @PutMapping("/blog/archives")
    @ResponseBody
    public Result updateBlogArchives(Archives archives) {
        int i = archivesService.updateBlogArchives(archives);
        return ResultUtil.success(i);
    }

    @DeleteMapping("/blog/archives/{ids}")
    @ResponseBody
    public Result deleteBlogArchives(@PathVariable Integer[] ids) {
        int i = archivesService.deleteBlogArchives(ids);
        return ResultUtil.success(i);
    }

}
