package com.dimple.project.front.controller;

import com.dimple.framework.aspectj.lang.annotation.VLog;
import com.dimple.framework.web.controller.BaseController;
import com.dimple.framework.web.domain.AjaxResult;
import com.dimple.framework.web.page.TableDataInfo;
import com.dimple.project.book.domain.Book;
import com.dimple.project.book.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: BookController
 * @description:
 * @author: Dimple
 * @date: 12/24/19
 */
@RestController("frontBookController")
@RequestMapping("f")
public class BookController extends BaseController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public TableDataInfo blog(Book book) {
        startPage();
        List<Book> bookList = bookService.selectBookList(book);
        return getDataTable(bookList);
    }

    @PutMapping("/book/like/{id}")
    @VLog(title = "点赞图书",pageId = "#id")
    public AjaxResult likeBook(@PathVariable Long id) {
        return AjaxResult.success(bookService.likeBook(id));
    }

    @GetMapping("/book/{id}")
    @VLog(title = "查看图书",pageId = "#id")
    public AjaxResult bookDetail(@PathVariable Long id) {
        return AjaxResult.success(bookService.getBookDetail(id));
    }

}
