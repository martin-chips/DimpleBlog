package com.dimple.project.blog.controller;

import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.domain.Category;
import com.dimple.project.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: CategoryController
 * @description: 分类Controller
 * @author: Dimple
 * @date: 2019-10-28
 */
@RestController
@RequestMapping("/blog/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService bgCategoryService;

    @PreAuthorize("@permissionService.hasPermission('blog:category:list')")
    @PostMapping("/list")
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = bgCategoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(Category category) {
        return toAjax(bgCategoryService.insertCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(Category category) {
        return toAjax(bgCategoryService.updateCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(String ids) {
        return toAjax(bgCategoryService.deleteCategoryByIds(ids));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        return toAjax(bgCategoryService.deleteCategoryById(id));
    }
}
