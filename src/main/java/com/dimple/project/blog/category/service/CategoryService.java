package com.dimple.project.blog.category.service;

import com.dimple.project.blog.category.domain.Category;

import java.util.List;

/**
 * @className: CategoryService
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface CategoryService {
    List<Category> selectCategoryList(Category category);

    /**
     * 新增分类
     *
     * @param category 分类实体类
     * @return 受影响的行数
     */
    int insertCategory(Category category);

    /**
     * 根据id获取category实体类
     *
     * @param categoryId 分类的id
     * @return category实体类
     */
    Category selectCategoryById(Integer categoryId);

    /**
     * 更改Category
     *
     * @param category 需要修改的category实体类
     * @return 受影响的行数
     */
    int updateCategory(Category category);

    /**
     * 根据id批量删除category
     *
     * @param ids 需要删除的category的id
     * @return 受影响的行数
     */
    int deleteCategoryByIds(Integer[] ids);

    /**
     * 更新分类的推荐状态
     *
     * @param categoryId 需要更新的分类的id
     * @param support    需要切换到的状态
     * @return 受影响的行数
     */
    int updateCategorySupportById(Integer categoryId, String support);

    /**
     * 校验分类名称是否唯一
     *
     * @param title 分类名称
     * @return 结果
     */
    String checkCategoryTitleUnique(String title);

    /**
     *
     * @return
     */
    List<Category> selectSupportCategoryList();
}
