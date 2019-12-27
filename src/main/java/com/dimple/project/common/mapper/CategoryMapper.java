package com.dimple.project.common.mapper;

import com.dimple.project.common.domain.Category;
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
     * @param category 分类
     * @return 分类集合
     */
    List<Category> selectCategoryList(Category category);

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 结果
     */
    int insertCategory(Category category);

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 结果
     */
    int updateCategory(Category category);

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
    int deleteCategoryByIds(@Param("ids") Long[] ids, @Param("username") String username);

    /**
     * 获取所有的Blog Category support的分类
     *
     * @return list
     */
    List<Category> selectSupportBlogCategoryList();

}
