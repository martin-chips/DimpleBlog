package com.dimple.project.book.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


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

    @GetMapping("/isbn/{isbn}")
    public AjaxResult getBookInfoByIsbn(@PathVariable String isbn) throws ParseException {
//        String result = HttpUtils.sendGet("  https://api.isoyu.com/books/isbn/", "isbn=" + isbn, "utf-8");
        String result = "{\n" +
                "    \"title\":\"深入理解计算机系统（原书第2版）\",\n" +
                "    \"author\":[\n" +
                "        {\n" +
                "            \"name\":\"（美）Randal E.Bryant\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"David O'Hallaron\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"logo\":\"https://img9.doubanio.com/view/subject/l/public/s4510534.jpg\",\n" +
                "    \"publisher\":\" 机械工业出版社\",\n" +
                "    \"published\":\" 2011-1-1\",\n" +
                "    \"page\":\" 702\",\n" +
                "    \"price\":\" 99.00元\",\n" +
                "    \"designed\":\" 平装\",\n" +
                "    \"description\":\"本书从程序员的视角详细阐述计算机系统的本质概念，并展示这些概念如何实实在在地影响应用程序的正确性、性能和实用性。全书共12章，主要内容包括信息的表示和处理、程序的机器级表示、处理器体系结构、优化程序性能、存储器层次结构、链接、异常控制流、虚拟存储器、系统级I/O、网络编程、并发编程等。书中提供大量的例子和练习，并给出部分答案，有助于读者加深对正文所述概念和知识的理解。\"\n" +
                "}";
        ParserConfig.getGlobalInstance().addAccept("java.util.Map");
        Map map = JSONObject.parseObject(result, Map.class);
        Book book = new Book();
        book.setTitle((String) map.get("title"));
        book.setPageNum(Integer.valueOf(((String) map.get("page")).trim()));
        book.setHeaderImg((String) map.get("logo"));
        List<Map<String, String>> authors = (List<Map<String, String>>) map.get("author");
        String author = "";
        for (Map<String, String> authorInfo : authors) {
            author += authorInfo.get("name");
        }
        book.setAuthor(author);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date published = dateFormat.parse((String) map.get("published"));
        book.setPublishTime(published);
        book.setPublisher((String) map.get("published"));
        book.setSummary((String) map.get("description"));
        return AjaxResult.success(book);
    }

    public static void main(String[] args) throws ParseException {
        new BookController(null).getBookInfoByIsbn("");
    }
}
