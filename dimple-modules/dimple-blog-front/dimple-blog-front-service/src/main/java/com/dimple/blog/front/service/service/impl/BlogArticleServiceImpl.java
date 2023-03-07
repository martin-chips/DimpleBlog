package com.dimple.blog.front.service.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.dimple.blog.front.service.entity.BlogArticle;
import com.dimple.blog.front.service.mapper.BlogArticleMapper;
import com.dimple.blog.front.service.service.BlogArticleService;
import com.dimple.blog.front.service.service.BlogArticleTagService;
import com.dimple.blog.front.service.service.BlogTagService;
import com.dimple.blog.front.service.service.bo.BlogArticleBO;
import com.dimple.blog.front.service.service.bo.BlogArticlePreNextItemBO;
import com.dimple.blog.front.service.service.bo.BlogArticlePrevNextBO;
import com.dimple.blog.front.service.service.bo.BlogArticleTagBO;
import com.dimple.blog.front.service.service.bo.BlogTagBO;
import com.dimple.common.core.utils.bean.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
        fillPvInfo(Arrays.asList(blogArticleBO));
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
        fillPvInfo(Arrays.asList(blogArticleBO));
        return blogArticleBO;
    }

    private void fillPvInfo(List<BlogArticleBO> blogArticleBOS) {
        List<Long> ids = blogArticleBOS.stream().map(BlogArticleBO::getId).collect(Collectors.toList());
        Map<Long, Long> articlePv = getArticlePvs(ids);
        for (BlogArticleBO blogArticleBO : blogArticleBOS) {
            blogArticleBO.setPv(articlePv.getOrDefault(blogArticleBO.getId(), 0L));
        }
    }

    private Map<Long, Long> getArticlePvs(Collection<Long> ids) {
        return blogArticleMapper.getPvByArticleId(ids).stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1));
    }

    private void fillArticleTags(BlogArticleBO blogArticleBO) {
        List<BlogArticleTagBO> blogArticleTagBOS = blogArticleTagService.selectBlogArticleTagByArticleId(blogArticleBO.getId());
        List<Long> tagIds = blogArticleTagBOS.stream().map(BlogArticleTagBO::getTagId).collect(Collectors.toList());
        List<String> blogTagBOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tagIds)) {
            blogTagBOS = blogTagService.selectBlogTagByIds(tagIds).stream().map(BlogTagBO::getTitle).collect(Collectors.toList());
        }
        blogArticleBO.setBlogTags(blogTagBOS);
    }

    @Override
    public List<BlogArticleBO> selectBlogArticleList(BlogArticleBO blogArticleBO) {
        BlogArticle blogArticle = BeanMapper.convert(blogArticleBO, BlogArticle.class);
        List<BlogArticle> blogArticles = blogArticleMapper.selectBlogArticleList(blogArticle);
        List<BlogArticleBO> blogArticleBOS = BeanMapper.convertList(blogArticles, BlogArticleBO.class);
        fillPvInfo(blogArticleBOS);
        return blogArticleBOS;
    }

    @Override
    public Map<Long, Long> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Maps.newHashMap();
        }
        return blogArticleMapper.selectBlogArticleCountByCategoryIds(categoryIds).stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1));
    }

    @Override
    public BlogArticlePrevNextBO selectPrevNextArticle(Long id) {
        List<BlogArticle> blogArticles = blogArticleMapper.selectBlogArticlePrevNext(id);
        BlogArticlePreNextItemBO prevBlogArticle = blogArticles.stream()
                .filter(e -> e.getId() < id)
                .findFirst()
                .map(e -> new BlogArticlePreNextItemBO(e.getId(), e.getHeaderImage(), e.getTitle()))
                .orElse(null);
        BlogArticlePreNextItemBO nextBlogArticle = blogArticles.stream()
                .filter(e -> e.getId() > id)
                .findFirst()
                .map(e -> new BlogArticlePreNextItemBO(e.getId(), e.getHeaderImage(), e.getTitle()))
                .orElse(null);
        return new BlogArticlePrevNextBO(prevBlogArticle, nextBlogArticle);

    }

    @Override
    public List<BlogArticleBO> selectBlogArticleListByTagId(Long tagId) {
        BlogArticleTagBO blogArticleTagBO = new BlogArticleTagBO();
        blogArticleTagBO.setTagId(tagId);
        List<BlogArticle> blogArticles = blogArticleMapper.selectBlogArticleByTagId(tagId);
        return BeanMapper.convertList(blogArticles, BlogArticleBO.class);
    }

    @Override
    public int likeArticle(Long id) {
        return blogArticleMapper.likeArticle(id);
    }
}
