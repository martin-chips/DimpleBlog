package com.dimple.project.book.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.book.mapper.BookMapper;
import com.dimple.project.book.domain.Book;
import com.dimple.project.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @className: BookServiceImpl
 * @description:
 * @author: Dimple
 * @date: 2019-11-27
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book selectBookById(Long id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public int deleteBookByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return bookMapper.deleteBookByIds(ConvertUtils.toLongArray(ids), username);
    }

    @Override
    public int likeBook(Long id) {
        return 0;
    }

    @Override
    public Book getBookDetail(Long id) {
        return null;
    }

    @Override
    public List<Book> selectBookList(Book book) {
        return bookMapper.selectBookList(book);
    }
}
