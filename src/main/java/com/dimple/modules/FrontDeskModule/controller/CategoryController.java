package com.dimple.modules.FrontDeskModule.controller;

import com.dimple.framework.message.Result;
import com.dimple.framework.message.ResultUtil;
import com.dimple.modules.BackStageModule.BlogManager.bean.Category;
import com.dimple.modules.BackStageModule.BlogManager.service.BlogService;
import com.dimple.modules.BackStageModule.BlogManager.service.CategoryService;
import com.dimple.modules.FrontDeskModule.domain.BlogDomain;
import com.dimple.modules.FrontDeskModule.domain.CategoryDomain;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @GetMapping("/category/{id}.html")
    public String categoriesPublicPage(@PathVariable int id, Model model) {
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "createTime");
        Page<BlogDomain> aLlBlogByCategoryId = blogService.getALlBlogByCategoryId(pageable, id);
        Category categoryById = categoryService.getCategoryById(id);
        model.addAttribute("blogs", aLlBlogByCategoryId);
        model.addAttribute("category", categoryById);
        return "front/category";
    }


    @GetMapping("/public/api/category")
    @ResponseBody
    public Result getCategories() {
        List<CategoryDomain> categoryDomains = categoryService.getSupportCategoryOrderByWeight();
        return ResultUtil.success(categoryDomains);
    }
}
