package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.api.RemoteBlogCategoryService;
import com.dimple.blog.api.bo.BlogCategoryBO;
import com.dimple.blog.api.model.BlogCategoryDTO;
import com.dimple.blog.front.service.service.BlogRestArticleService;
import com.dimple.blog.front.service.service.BlogRestCategoryService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.PageUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.github.pagehelper.Page;
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
public class BlogRestCategoryServiceImpl implements BlogRestCategoryService {
    @Autowired
    private RemoteBlogCategoryService remoteBlogCategoryService;
    @Autowired
    private BlogRestArticleService blogRestArticleService;

    @Override
    public List<BlogCategoryBO> selectBlogCategoryList(BlogCategoryBO blogCategory) {
        ResponseEntity<List<BlogCategoryBO>> blogCategoryListResponse = remoteBlogCategoryService.selectBlogCategoryList(BeanMapper.convert(blogCategory, BlogCategoryDTO.class), SecurityConstants.INNER);
        Page<BlogCategoryBO> blogCategories = PageUtils.getInnerPage(blogCategoryListResponse);
        Map<Long, Long> categoryIdAndCountMap = blogRestArticleService.selectBlogArticleCountByCategoryIds(blogCategories.stream().map(e -> e.getId()).collect(Collectors.toSet()));
        List<BlogCategoryBO> blogCategoryBOS = BeanMapper.convertList(blogCategories, BlogCategoryBO.class);
        return blogCategoryBOS.stream()
                .map(e -> {
                    e.setArticleCount(categoryIdAndCountMap.getOrDefault(e.getId(), 0L));
                    return e;
                }).collect(Collectors.toList());
    }

}
