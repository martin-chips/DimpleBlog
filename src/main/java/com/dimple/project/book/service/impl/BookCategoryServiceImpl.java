package com.dimple.project.book.service.impl;

import com.dimple.common.enums.CategoryType;
import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.book.service.BookCategoryService;
import com.dimple.project.common.domain.Category;
import com.dimple.project.common.mapper.CategoryMapper;
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
    CategoryMapper categoryMapper;

    @Override
    public Category selectBookCategoryById(Long id) {
        return categoryMapper.selectCategoryById(id);
    }

    @Override
    public List<Category> selectBookCategoryList(Category category) {
        category.setType(CategoryType.BOOK.getType());
        return categoryMapper.selectCategoryList(category);
    }

    @Override
    public int insertBookCategory(Category category) {
        category.setType(CategoryType.BOOK.getType());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public int updateBookCategory(Category category) {
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int deleteBookCategoryByIds(String ids) {
        return categoryMapper.deleteCategoryByIds(ConvertUtils.toLongArray(ids), SecurityUtils.getUsername());
    }
}
