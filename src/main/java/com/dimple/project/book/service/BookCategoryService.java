package com.dimple.project.book.service;

import com.dimple.project.book.entity.BookCategory;

import java.util.List;

/**
 * @className: BookCategoryService
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
public interface BookCategoryService {

    /**
     * 查询分类
     *
     * @param id 分类ID
     * @return 分类
     */
    BookCategory selectBookCategoryById(Long id);

    /**
     * 查询分类列表
     *
     * @param bookCategory 分类
     * @return 分类集合
     */
    List<BookCategory> selectBookCategoryList(BookCategory bookCategory);

    /**
     * 新增分类
     *
     * @param bookCategory 分类
     * @return 结果
     */
    int insertBookCategory(BookCategory bookCategory);

    /**
     * 修改分类
     *
     * @param bookCategory 分类
     * @return 结果
     */
    int updateBookCategory(BookCategory bookCategory);

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBookCategoryByIds(String ids);

}
