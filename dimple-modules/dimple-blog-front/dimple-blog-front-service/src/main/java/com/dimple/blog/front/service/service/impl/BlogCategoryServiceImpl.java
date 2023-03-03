package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogCategory;
import com.dimple.blog.front.service.mapper.BlogCategoryMapper;
import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.BlogCategoryService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.service.service.bo.BlogCategoryBO;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return blogCategoryBO;
    }

    @Override
    public List<BlogCategoryBO> selectBlogCategoryList(BlogCategoryBO blogCategory) {
        BlogCategory category = BeanMapper.convert(blogCategory, BlogCategory.class);
        List<BlogCategory> blogCategories = blogCategoryMapper.selectBlogCategoryList(category);
        Map<Long, Long> categoryIdAndCountMap = blogArticleService.selectBlogArticleCountByCategoryIds(blogCategories.stream().map(e -> e.getId()).collect(Collectors.toSet()));
        List<BlogCategoryBO> blogCategoryBOS = BeanMapper.convertList(blogCategories, BlogCategoryBO.class);
        return blogCategoryBOS.stream()
                .map(e -> {
                    e.setArticleCount(categoryIdAndCountMap.getOrDefault(e.getId(), 0L));
                    return e;
                }).collect(Collectors.toList());
    }

}
