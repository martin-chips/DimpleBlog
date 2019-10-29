package com.dimple.project.blog.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.DateUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.blog.domain.Category;
import com.dimple.project.blog.mapper.CategoryMapper;
import com.dimple.project.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: CategoryServiceImpl
 * @description: 分类Service业务层处理
 * @author: Dimple
 * @date: 2019-10-28
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper bgCategoryMapper;

    @Override
    public Category selectCategoryById(Long id) {
        return bgCategoryMapper.selectCategoryById(id);
    }

    @Override
    public List<Category> selectCategoryList(Category bgCategory) {
        return bgCategoryMapper.selectCategoryList(bgCategory);
    }

    @Override
    public int insertCategory(Category bgCategory) {
        return bgCategoryMapper.insertCategory(bgCategory);
    }

    @Override
    public int updateCategory(Category bgCategory) {
        bgCategory.setUpdateTime(DateUtils.getNowDate());
        return bgCategoryMapper.updateCategory(bgCategory);
    }

    @Override
    public int deleteCategoryByIds(String ids) {
        String username = SecurityUtils.getUsername();
        return bgCategoryMapper.deleteCategoryByIds(ConvertUtils.toStrArray(ids), username);
    }

    @Override
    public int deleteCategoryById(Long id) {
        String username = SecurityUtils.getUsername();
        return bgCategoryMapper.deleteCategoryById(id, username);
    }
}
