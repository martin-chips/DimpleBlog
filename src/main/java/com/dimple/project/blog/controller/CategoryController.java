package com.dimple.project.blog.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.blog.service.CategoryService;
import com.dimple.project.common.domain.Category;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated Category category) {
        category.setCreateBy(SecurityUtils.getUsername());
        return toAjax(categoryService.insertCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(categoryService.selectCategoryById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated Category category) {
        return toAjax(categoryService.updateCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('blog:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(categoryService.deleteCategoryByIds(ids));
    }
}
