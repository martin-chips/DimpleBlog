package com.dimple.project.book.controller;

import com.dimple.common.utils.SecurityUtils;
import com.dimple.framework.aspectj.lang.annotation.Log;
import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.book.domain.Book;
import com.dimple.project.book.service.BookService;
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
 * @className: BookController
 * @description: 图书Controller
 * @author: Dimple
 * @date: 2019-11-27
 */
@RestController
@RequestMapping("book/book")
public class BookController extends BaseController {
    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("@permissionService.hasPermission('book:book:list')")
    @GetMapping("/list")
    public TableDataInfo list(Book book) {
        startPage();
        List<Book> list = bookService.selectBookList(book);
        return getDataTable(list);
    }

    @PreAuthorize("@permissionService.hasPermission('book:book:add')")
    @Log(title = "图书管理", businessType = BusinessType.INSERT)
    @PostMapping()
    public AjaxResult add(@RequestBody @Validated Book book) {
        book.setCreateBy(SecurityUtils.getUsername());
        return toAjax(bookService.insertBook(book));
    }

    @PreAuthorize("@permissionService.hasPermission('book:book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(bookService.selectBookById(id));
    }

    @PreAuthorize("@permissionService.hasPermission('book:book:edit')")
    @Log(title = "图书管理", businessType = BusinessType.UPDATE)
    @PutMapping()
    public AjaxResult edit(@RequestBody @Validated Book book) {
        return toAjax(bookService.updateBook(book));
    }

    @PreAuthorize("@permissionService.hasPermission('book:book:remove')")
    @Log(title = "图书管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(bookService.deleteBookByIds(ids));
    }

}
