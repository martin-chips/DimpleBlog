package com.dimple.controller;

import com.dimple.bean.Category;
import com.dimple.service.CategoryService;
import com.dimple.utils.message.Result;
import com.dimple.utils.message.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: CategoriesController
 * @Description: 博客类别
 * @Auther: Dimple
 * @Date: 2018/12/11 19:29
 * @Version: 1.0
 */
@Controller
@Api("博客类别")
public class CategoriesController {

    @Autowired
    CategoryService categoryService;

    /**
     * 跳转到归档页面
     *
     * @return
     */
    @GetMapping("/blog/category.html")
    public String categoryPage(Model model) {
        List<Category> allBlogCategory = categoryService.getAllBlogCategory();
        model.addAttribute("categories", allBlogCategory);
        return "blog/category/category";
    }

    @GetMapping("/blog/category/{id}.html")
    public String categoryUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.selectBlogCategoryByCategoryId(id));
        return "blog/category/update";
    }

    @GetMapping("/blog/category/add.html")
    public String categoryAddPage() {
        return "blog/category/add";
    }

    @ApiOperation("有条件的获取所有的分类数据")
    @GetMapping("/blog/category.json")
    @ResponseBody
    public Result getAllBlogcategory(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "title", required = false) String title) {
        PageHelper.startPage(pageNum, pageSize, "update_time desc");
        List<Category> allBlogCategory = categoryService.getAllBlogCategory(startTime, endTime, description, title);
        PageInfo pageInfo = new PageInfo(allBlogCategory);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation("无条件获取所有的分类数据")
    @GetMapping("/blog/category")
    @ResponseBody
    public Result getAllBlogCategoris() {
        return ResultUtil.success(categoryService.getAllBlogCategory());
    }


    @ApiOperation("新增分类")
    @PostMapping("/blog/category")
    @ResponseBody
    public Result insertBlogCategory(Category category) {
        int i = categoryService.insertBlogCategory(category);
        return ResultUtil.success(i);
    }

    @ApiOperation("修改分类")
    @PutMapping("/blog/category")
    @ResponseBody
    public Result updateBlogCategory(Category category) {
        int i = categoryService.updateBlogCategory(category);
        return ResultUtil.success(i);
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/blog/category/{ids}")
    @ResponseBody
    public Result deleteBlogCategory(@PathVariable Integer[] ids) {
        int i = categoryService.deleteBlogCategory(ids);
        return ResultUtil.success(i);
    }
}
