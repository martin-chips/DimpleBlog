package com.dimple.modules.endModule.blogManager.service;

import com.dimple.modules.endModule.blogManager.bean.Category;
import com.dimple.modules.frontModule.front.domain.CategoryDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<Category> getAllBlogCategory(Date startTime, Date endTime, String description, String title, Pageable pageable);

    /**
     * 获取所有的分类类别
     *
     * @return
     */
    List<Category> getAllBlogCategory();

    /**
     * 新增归档
     *
     * @param category
     * @return
     */
    Category insertBlogCategory(Category category);

    /**
     * 更新归档
     *
     * @param category
     * @return
     */
    Category updateBlogCategory(Category category);

    /**
     * 删除归档
     *
     * @param categoryId
     * @return
     */
    int deleteBlogCategory(Integer[] categoryId);

    /**
     * 根据ID获取归档信息
     *
     * @param categoryId
     * @return
     */
    Category selectBlogCategoryByCategoryId(Integer categoryId);

    /**
     * 更新分类是是否上推荐
     *
     * @param ids
     * @param status 该分类当前的状态，修改的时候会修改为其反状态
     * @return
     */
    int updateCategorySupport(Integer[] ids, Boolean status);


    Category getCategoryByTitle(String title);

    List<CategoryDomain> getSupportCategories();

    /**
     * 根据Id获取Category的信息
     *
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);
}
