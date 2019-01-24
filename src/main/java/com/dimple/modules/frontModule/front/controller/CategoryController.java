package com.dimple.modules.frontModule.front.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.endModule.blogManager.bean.Blog;
import com.dimple.modules.endModule.blogManager.service.BlogService;
import com.dimple.modules.endModule.blogManager.service.CategoryService;
import com.dimple.modules.frontModule.front.domain.CategoryDomain;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @className: FrontCategoryController
 * @description:
 * @auther: Owenb
 * @date: 01/21/19 20:56
 * @version: 1.0
 */
@Controller("frontCategoryController")
public class CategoryController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @ApiOperation("获取id为Category下的博客的信息")
    @GetMapping("/public/page/category/{id}.html")
    public String categoriesPublicPage(@PathVariable int id, Model model) {
        List<Blog> blogs = blogService.getAllBlogByCategoryId(id);
        model.addAttribute("blogs", blogs);
        return "front/archive";
    }


    @GetMapping("/public/api/category")
    @ResponseBody
    public Result getCategories() {
        List<CategoryDomain> categoryDomains = categoryService.getSupportCategories();
        return ResultUtil.success(categoryDomains);
    }
}
