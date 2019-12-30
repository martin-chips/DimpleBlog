package com.dimple.project.blog.service.impl;

import com.dimple.common.utils.ConvertUtils;
import com.dimple.common.utils.DateUtils;
import com.dimple.common.utils.ObjectUtils;
import com.dimple.common.utils.SecurityUtils;
import com.dimple.project.blog.domain.Blog;
import com.dimple.project.blog.mapper.BlogMapper;
import com.dimple.project.blog.service.CategoryService;
import com.dimple.project.common.domain.Category;
import com.dimple.project.common.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: CategoryServiceImpl
 * @description: 分类Service业务层处理
 * @author: Dimple
 * @date: 2019-10-28
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    final CategoryMapper bgCategoryMapper;
    final BlogMapper blogMapper;

    public CategoryServiceImpl(CategoryMapper bgCategoryMapper, BlogMapper blogMapper) {
        this.bgCategoryMapper = bgCategoryMapper;
        this.blogMapper = blogMapper;
    }

    @Override

    public Category selectCategoryById(Long id) {
        return bgCategoryMapper.selectCategoryById(id);
    }

    @Override
    public List<Category> selectCategoryList(Category bgCategory) {
        List<Category> categoryList = bgCategoryMapper.selectCategoryList(bgCategory);
        List<Long> categoryIds = categoryList.stream().map(Category::getId).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(categoryIds)) {
            return categoryList;
        }
        List<Blog> blogList = blogMapper.selectBlogListByCategoryIds(categoryIds);
        for (Category category : categoryList) {
            List<Blog> collect = blogList.stream().filter(e -> category.getId().equals(e.getCategoryId())).collect(Collectors.toList());
            category.setBlogList(collect);
        }
        return categoryList;
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
        return bgCategoryMapper.deleteCategoryByIds(ConvertUtils.toLongArray(ids), username);
    }

    @Override
    public int deleteCategoryById(Long id) {
        String username = SecurityUtils.getUsername();
        return bgCategoryMapper.deleteCategoryById(id, username);
    }

    @Override
    public List<Category> selectSupportCategory() {
        return bgCategoryMapper.selectSupportBlogCategoryList();
    }
}


