package com.dimple.modules.endModule.blogManager.controller;

import com.dimple.modules.endModule.blogManager.bean.Category;
import com.dimple.framework.enums.OperateType;
import com.dimple.framework.log.annotation.Log;
import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.blogManager.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

/**
 * @ClassName: CategoriesController
 * @Description: 博客类别
 * @Auther: Dimple
 * @Date: 2018/12/11 19:29
 * @Version: 1.0
 */
@Controller
@Api("博客类别Controller")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 跳转到归档页面
     *
     * @return
     */
    @GetMapping("/page/category.html")
    @ApiIgnore
    public String categoryPage(Model model) {
        List<Category> allBlogCategory = categoryService.getAllBlogCategory();
        model.addAttribute("categories", allBlogCategory);
        return "blog/category/category";
    }

    @GetMapping("/page/category/{id}.html")
    @ApiIgnore
    public String categoryUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("category", categoryService.selectBlogCategoryByCategoryId(id));
        return "blog/category/update";
    }

    @GetMapping("/page/categoryAdd.html")
    @ApiIgnore
    public String categoryAddPage() {
        return "blog/category/add";
    }

    @ApiOperation("有条件的获取所有的分类数据")
    @GetMapping("/api/category")
    @Log(title = "博客分类获取", operateType = OperateType.OTHER)
    @ResponseBody
    public Result getAllBlogcategory(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "startTime", required = false) Date startTime,
                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "endTime", required = false) Date endTime,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "title", required = false) String title) {
        Pageable pageable = PageRequest.of(pageNum < 0 ? 0 : pageNum, pageSize, Sort.Direction.DESC, "createTime");
        Page<Category> allBlogCategory = categoryService.getAllBlogCategory(startTime, endTime, description, title, pageable);
        return ResultUtil.success(allBlogCategory);
    }

    @ApiOperation("无条件获取所有的分类数据")
    @GetMapping("/api/categoryNoCondition")
    @ResponseBody
    public Result getAllBlogCategoris() {
        return ResultUtil.success(categoryService.getAllBlogCategory());
    }


    @ApiOperation("新增分类")
    @PostMapping("/api/category")
    @ResponseBody
    public Result insertBlogCategory(Category category) {
        Category insertBlogCategory = categoryService.insertBlogCategory(category);
        return ResultUtil.success(insertBlogCategory);
    }

    @ApiOperation("修改分类")
    @PutMapping("/api/category")
    @ResponseBody
    public Result updateBlogCategory(Category category) {
        Category updateBlogCategory = categoryService.updateBlogCategory(category);
        return ResultUtil.success(updateBlogCategory);
    }

    @ApiOperation("删除分类")
    @DeleteMapping("/api/category/{ids}")
    @ResponseBody
    public Result deleteBlogCategory(@PathVariable Integer[] ids) {
        int i = categoryService.deleteBlogCategory(ids);
        return ResultUtil.success(i);
    }

    @ApiOperation("推荐分类上首页")
    @PutMapping("/api/category/support/{id}/{status}")
    @ResponseBody
    public Result supportCategory(@PathVariable(value = "id") Integer[] ids, @PathVariable Boolean status) {
        int i = categoryService.updateCategorySupport(ids, status);
        return ResultUtil.success(i);
    }

}
