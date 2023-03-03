package com.dimple.blog.front.service.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.dimple.blog.front.service.entity.BlogArticle;
import com.dimple.blog.front.service.mapper.BlogArticleMapper;
import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.BlogArticleTagService;
import com.dimple.blog.front.service.service.BlogTagService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.service.service.bo.BlogArticleTagBO;
import com.dimple.blog.front.service.service.bo.BlogTagBO;
import com.dimple.common.core.utils.bean.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Blog articleService业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
@Slf4j
public class BlogArticleServiceImpl implements BlogArticleService {
    @Autowired
    private BlogArticleMapper blogArticleMapper;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogArticleTagService blogArticleTagService;

    @Override
    public BlogArticleBO selectBlogArticleById(Long id) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticleMapper.selectBlogArticleDetailById(id), BlogArticleBO.class);
        fillArticleTags(blogArticleBO);

        return blogArticleBO;
    }

    @Override
    public List<BlogArticleBO> selectBlogArticleByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        List<BlogArticle> blogArticles = blogArticleMapper.selectBlogArticleByIds(ids);
        return BeanMapper.convertList(blogArticles, BlogArticleBO.class);
    }

    @Override
    public BlogArticleBO selectBlogArticleDetailById(Long id) {
        BlogArticleBO blogArticleBO = BeanMapper.convert(blogArticleMapper.selectBlogArticleDetailById(id), BlogArticleBO.class);
        fillArticleTags(blogArticleBO);
        return blogArticleBO;
    }

    private void fillArticleTags(BlogArticleBO blogArticleBO) {
        List<BlogArticleTagBO> blogArticleTagBOS = blogArticleTagService.selectBlogArticleTagByArticleId(blogArticleBO.getId());
        List<Long> tagIds = blogArticleTagBOS.stream().map(BlogArticleTagBO::getTagId).collect(Collectors.toList());
        List<BlogTagBO> blogTagBOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tagIds)) {
            blogTagBOS = blogTagService.selectBlogTagByIds(tagIds);
        }
        blogArticleBO.setBlogTags(blogTagBOS);
    }

    @Override
    public List<BlogArticleBO> selectBlogArticleList(BlogArticleBO blogArticleBO) {
        BlogArticle blogArticle = BeanMapper.convert(blogArticleBO, BlogArticle.class);
        List<BlogArticle> blogArticles = blogArticleMapper.selectBlogArticleList(blogArticle);
        return BeanMapper.convertList(blogArticles, BlogArticleBO.class);
    }

    @Override
    public Map<Long, Long> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Maps.newHashMap();
        }
        return blogArticleMapper.selectBlogArticleCountByCategoryIds(categoryIds).stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1));
    }
}
