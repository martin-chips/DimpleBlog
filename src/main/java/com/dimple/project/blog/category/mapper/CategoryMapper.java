package com.dimple.project.blog.category.mapper;

import com.dimple.project.blog.category.domain.Category;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据id更新推荐状态
     *
     * @param categoryId 需要更新的id
     * @param support    需要切换的状态
     * @return 受影响的行数
     */
    int updateCategorySupportById(@Param("categoryId") Integer categoryId, @Param("support") String support);

    /**
     * 根据title获取category实体
     *
     * @param title 需要的category的标题
     * @return category实体
     */
    Category selectCategoryByCategoryTitle(String title);

    /**
     * 获取推荐的分类
     *
     * @return
     */
    List<Category> selectSupportCategoryList();
}
