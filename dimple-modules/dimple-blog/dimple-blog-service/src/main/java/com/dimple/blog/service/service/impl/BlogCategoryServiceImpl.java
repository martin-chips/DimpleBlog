package com.dimple.blog.service.service.impl;

import com.dimple.blog.service.entity.BlogCategory;
import com.dimple.blog.service.mapper.BlogCategoryMapper;
import com.dimple.blog.service.service.BlogArticleService;
import com.dimple.blog.service.service.BlogCategoryService;
import com.dimple.blog.service.service.bo.BlogArticleBO;
import com.dimple.blog.service.service.bo.BlogCategoryBO;
import com.dimple.common.core.utils.DateUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * blog categoryService业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private BlogArticleService blogArticleService;

    @Override
    public BlogCategoryBO selectBlogCategoryById(Long id) {
        BlogCategoryBO blogCategoryBO = BeanMapper.convert(blogCategoryMapper.selectBlogCategoryById(id), BlogCategoryBO.class);
        BlogArticleBO blogArticleBO = new BlogArticleBO();
        blogArticleBO.setCategoryId(id);
        List<BlogArticleBO> blogArticleBOS = blogArticleService.selectBlogArticleList(blogArticleBO);
        blogCategoryBO.setArticles(blogArticleBOS);
        return blogCategoryBO;
    }

    @Override
    public List<BlogCategoryBO> selectBlogCategoryByIds(Collection<Long> ids) {
        List<BlogCategory> blogCategories = blogCategoryMapper.selectBlogCategoryByIds(ids);
        return BeanMapper.convertList(blogCategories, BlogCategoryBO.class);
    }

    @Override
    public List<BlogCategoryBO> selectBlogCategoryList(BlogCategoryBO blogCategory) {
        BlogCategory category = BeanMapper.convert(blogCategory, BlogCategory.class);
        List<BlogCategory> blogCategories = blogCategoryMapper.selectBlogCategoryList(category);
        return BeanMapper.convertList(blogCategories, BlogCategoryBO.class);
    }

    @Override
    public int insertBlogCategory(BlogCategoryBO blogCategoryBO) {
        BlogCategory blogCategory = BeanMapper.convert(blogCategoryBO, BlogCategory.class);
        blogCategory.setCreateTime(DateUtils.getNowDate());
        return blogCategoryMapper.insertBlogCategory(blogCategory);
    }

    @Override
    public int updateBlogCategory(BlogCategoryBO blogCategoryBO) {
        BlogCategory blogCategory = BeanMapper.convert(blogCategoryBO, BlogCategory.class);
        blogCategory.setUpdateTime(DateUtils.getNowDate());
        return blogCategoryMapper.updateBlogCategory(blogCategory);
    }

    @Override
    public int deleteBlogCategoryByIds(Long[] ids) {
        return blogCategoryMapper.deleteBlogCategoryByIds(ids);
    }

    @Override
    public int deleteBlogCategoryById(Long id) {
        return blogCategoryMapper.deleteBlogCategoryById(id);
    }
}
