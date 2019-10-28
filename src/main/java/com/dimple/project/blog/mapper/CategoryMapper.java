package com.dimple.project.blog.mapper;

import com.dimple.project.blog.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: CategoryMapper
 * @description: 分类Mapper接口
 * @author: Dimple
 * @date: 2019-10-28
 */
public interface CategoryMapper {
    /**
     * 查询分类
     *
     * @param id 分类ID
     * @return 分类
     */
    Category selectCategoryById(Long id);

    /**
     * 查询分类列表
     *
     * @param bgCategory 分类
     * @return 分类集合
     */
    List<Category> selectCategoryList(Category bgCategory);

    /**
     * 新增分类
     *
     * @param bgCategory 分类
     * @return 结果
     */
    int insertCategory(Category bgCategory);

    /**
     * 修改分类
     *
     * @param bgCategory 分类
     * @return 结果
     */
    int updateCategory(Category bgCategory);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return 结果
     */
    int deleteCategoryById(@Param("id") Long id, @Param("username") String username);

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteCategoryByIds(@Param("ids") String[] ids, @Param("username") String username);
}
