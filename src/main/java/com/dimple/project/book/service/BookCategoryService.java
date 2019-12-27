package com.dimple.project.book.service;

import com.dimple.project.common.domain.Category;

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
    Category selectBookCategoryById(Long id);

    /**
     * 查询分类列表
     *
     * @param category 分类
     * @return 分类集合
     */
    List<Category> selectBookCategoryList(Category category);

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 结果
     */
    int insertBookCategory(Category category);

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 结果
     */
    int updateBookCategory(Category category);

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBookCategoryByIds(String ids);

}
