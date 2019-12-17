package com.dimple.project.book.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.book.entity.BookCategory;
import com.dimple.project.book.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    BookCategoryService bookCategoryService;

    @PreAuthorize("@permissionService.hasPermission('book:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookCategory bookCategory) {
        startPage();
        List<BookCategory> list = bookCategoryService.selectBookCategoryList(bookCategory);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:add')")
    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated BookCategory bookCategory) {
        bookCategory.setCreateBy(SecurityUtils.getUsername());
        return toAjax(bookCategoryService.insertBookCategory(bookCategory));
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(bookCategoryService.selectBookCategoryById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:edit')")
    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated BookCategory bookCategory) {
        return toAjax(bookCategoryService.updateBookCategory(bookCategory));
    }

    @PreAuthorize("@permissionService.hasPermission('book:category:remove')")
    @Log(title = "分类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(bookCategoryService.deleteBookCategoryByIds(ids));
    }
}
