package com.dimple.project.book.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.book.entity.BookCategory;
import com.dimple.project.book.mapper.BookCategoryMapper;
import com.dimple.project.book.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: BookCategoryServiceImpl
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Override
    public BookCategory selectBookCategoryById(Long id) {
        return bookCategoryMapper.selectBookCategoryById(id);
    }

    @Override
    public List<BookCategory> selectBookCategoryList(BookCategory bookCategory) {
        return bookCategoryMapper.selectBookCategoryList(bookCategory);
    }

    @Override
    public int insertBookCategory(BookCategory bookCategory) {
        return bookCategoryMapper.insertBookCategory(bookCategory);
    }

    @Override
    public int updateBookCategory(BookCategory bookCategory) {
        return bookCategoryMapper.updateBookCategory(bookCategory);
    }

    @Override
    public int deleteBookCategoryByIds(String ids) {
        return bookCategoryMapper.deleteBookCategoryByIds(ConvertUtils.toLongArray(ids), SecurityUtils.getUsername());
    }
}
