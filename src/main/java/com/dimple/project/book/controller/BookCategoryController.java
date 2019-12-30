package com.dimple.project.book.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.book.service.BookCategoryService;
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
 * @className: BookCategoryController
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
@RestController
@RequestMapping("book/category")
public class BookCategoryController extends BaseController {
    final BookCategoryService bookCategoryService;

    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = bookCategoryService.selectBookCategoryList(category);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated Category category) {
        category.setCreateBy(SecurityUtils.getUsername());
        return toAjax(bookCategoryService.insertBookCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(bookCategoryService.selectBookCategoryById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated Category category) {
        return toAjax(bookCategoryService.updateBookCategory(category));
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(bookCategoryService.deleteBookCategoryByIds(ids));
    }
}
