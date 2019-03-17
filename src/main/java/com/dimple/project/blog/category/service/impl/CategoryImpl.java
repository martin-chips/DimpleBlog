package com.dimple.project.blog.category.service.impl;

import com.dimple.common.utils.security.ShiroUtils;
import com.dimple.project.blog.category.domain.Category;
import com.dimple.project.blog.category.mapper.CategoryMapper;
import com.dimple.project.blog.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: CategoryImpl
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategoryList(Category category) {
        return categoryMapper.selectCategoryList(category);
    }

    @Override
    public int insertCategory(Category category) {
        category.setCreateBy(ShiroUtils.getLoginName());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public Category selectCategoryById(Integer categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }

    @Override
    public int updateCategory(Category category) {
        category.setUpdateBy(ShiroUtils.getLoginName());
        return categoryMapper.updateCategory(category);
    }

    @Override
    public int deleteCategoryByIds(Integer[] ids) {
        return categoryMapper.deleteCategoryByIds(ids);
    }
}
