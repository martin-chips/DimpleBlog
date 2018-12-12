package com.dimple.service;

import com.dimple.bean.Category;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: categoryService
 * @Description:
 * @Auther: Dimple
 * @Date: 2018/12/11 19:30
 * @Version: 1.0
 */
public interface CategoryService {
    List<Category> getAllBlogCategory(Date startTime, Date endTime, String description, String title);

    /**
     * 新增归档
     *
     * @param category
     * @return
     */
    int insertBlogCategory(Category category);

    /**
     * 更新归档
     *
     * @param category
     * @return
     */
    int updateBlogCategory(Category category);

    /**
     * 删除归档
     *
     * @param categoryId
     * @return
     */
    int deleteBlogCategory(Integer categoryId[]);

    /**
     * 根据ID获取归档信息
     *
     * @param categoryId
     * @return
     */
    Category selectBlogCategoryByCategoryId(Integer categoryId);
}
