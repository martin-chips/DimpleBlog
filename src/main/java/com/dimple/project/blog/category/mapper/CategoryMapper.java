package com.dimple.project.blog.category.mapper;

import com.dimple.project.blog.category.domain.Category;

import java.util.List;

/**
 * @className: CategoryMapper
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
public interface CategoryMapper {
    /**
     * 根据条件选择Category集合
     *
     * @param category 带有条件的category
     * @return 符合条件的category实体类
     */
    List<Category> selectCategoryList(Category category);

    /**
     * 新增Category
     *
     * @param category 需要新增的Category实体类的信息
     * @return 受影响的行数
     */
    int insertCategory(Category category);

    /**
     * 根据id获取Category的信息
     *
     * @param categoryId 需要获取得Category实体类的id
     * @return Category实体类
     */
    Category selectCategoryById(Integer categoryId);

    /**
     * 更新Category
     *
     * @param category 需要更新的Category实体类的信息
     * @return 受影响的行数
     */
    int updateCategory(Category category);

    /**
     * 批量删除category
     *
     * @param ids 需要删除的category的id
     * @return 受影响的行数
     */
    int deleteCategoryByIds(Integer[] ids);
}
