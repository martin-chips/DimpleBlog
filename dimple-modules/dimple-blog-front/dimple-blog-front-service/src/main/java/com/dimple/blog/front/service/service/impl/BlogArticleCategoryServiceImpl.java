package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogArticleCategory;
import com.dimple.blog.front.service.mapper.BlogArticleCategoryMapper;
import com.dimple.blog.front.service.service.BlogArticleCategoryService;
import com.dimple.blog.front.service.service.bo.BlogArticleCategoryBO;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
public class BlogArticleCategoryServiceImpl implements BlogArticleCategoryService {
    @Autowired
    private BlogArticleCategoryMapper blogArticleCategoryMapper;

    @Override
    public BlogArticleCategoryBO selectBlogArticleCategoryById(Long id) {
        return BeanMapper.convert(blogArticleCategoryMapper.selectBlogArticleCategoryById(id), BlogArticleCategoryBO.class);
    }

    @Override
    public List<BlogArticleCategoryBO> selectBlogArticleCategoryList(BlogArticleCategoryBO blogArticleCategoryBO) {
        BlogArticleCategory blogArticleCategory = BeanMapper.convert(blogArticleCategoryBO, BlogArticleCategory.class);
        List<BlogArticleCategory> blogArticleCategories = blogArticleCategoryMapper.selectBlogArticleCategoryList(blogArticleCategory);
        return BeanMapper.convertList(blogArticleCategories, BlogArticleCategoryBO.class);
    }

}
