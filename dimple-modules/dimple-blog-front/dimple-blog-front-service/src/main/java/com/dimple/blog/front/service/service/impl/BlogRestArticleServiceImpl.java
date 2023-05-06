package com.dimple.blog.front.service.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Maps;
import com.dimple.blog.api.RemoteBlogArticleService;
import com.dimple.blog.api.RemoteBlogCommentService;
import com.dimple.blog.api.bo.*;
import com.dimple.blog.api.model.BlogArticleDTO;
import com.dimple.blog.front.service.service.BlogRestArticleService;
import com.dimple.blog.front.service.service.BlogRestArticleTagService;
import com.dimple.blog.front.service.service.BlogRestTagService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.exception.article.ArticleNotFoundException;
import com.dimple.common.core.utils.PageUtils;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Blog articleService业务层处理
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Service
@Slf4j
public class BlogRestArticleServiceImpl implements BlogRestArticleService {
    @Autowired
    RemoteBlogArticleService remoteBlogArticleService;
    @Autowired
    private BlogRestTagService blogRestTagService;
    @Autowired
    private BlogRestArticleTagService blogRestArticleTagService;
    @Autowired
    private RemoteBlogCommentService remoteBlogCommentService;

    @Override
    public BlogArticleBO selectBlogArticleById(Long id) {
        ResponseEntity<BlogArticleBO> blogArticleBOResponseEntity = remoteBlogArticleService.selectBlogArticleDetailById(id, SecurityConstants.INNER);
        BlogArticleBO blogArticleBO = null;
        if (blogArticleBOResponseEntity.isOk()) {
            blogArticleBO = blogArticleBOResponseEntity.getData();
            if (Objects.isNull(blogArticleBO)) {
                throw new ArticleNotFoundException();
            }
            fillArticleTags(blogArticleBO);
            fillPvInfo(Arrays.asList(blogArticleBO));
        }
        return blogArticleBO;
    }


    @Override
    public BlogArticleBO selectBlogArticleDetailById(Long id) {
        ResponseEntity<BlogArticleBO> blogArticleBOResponseEntity = remoteBlogArticleService.selectBlogArticleDetailById(id, SecurityConstants.INNER);
        BlogArticleBO blogArticleBO = null;
        if (blogArticleBOResponseEntity.isOk()) {
            blogArticleBO = blogArticleBOResponseEntity.getData();
            fillArticleTags(blogArticleBO);
            fillPvInfo(Arrays.asList(blogArticleBO));
        }
        return blogArticleBO;
    }

    private void fillCommentCountInfo(List<BlogArticleBO> blogArticleBOS) {
        Set<Long> articleIds = blogArticleBOS.stream().map(BlogArticleBO::getId).collect(Collectors.toSet());
        if (CollectionUtils.isEmpty(articleIds)) {
            return;
        }
        ResponseEntity<List<KeyValue<Long, Long>>> keyValueListResponse = remoteBlogCommentService.selectBlogCommentCountByArticleId(articleIds, SecurityConstants.INNER);
        Map<Long, Long> articleIdAndCountMap = Maps.newHashMap();
        if (keyValueListResponse.isOk()) {
            articleIdAndCountMap = keyValueListResponse.getData().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        }
        for (BlogArticleBO blogArticleBO : blogArticleBOS) {
            blogArticleBO.setCommentCount(articleIdAndCountMap.getOrDefault(blogArticleBO.getId(), 0L));
        }
    }

    private void fillPvInfo(List<BlogArticleBO> blogArticleBOS) {
        List<Long> ids = blogArticleBOS.stream().map(BlogArticleBO::getId).collect(Collectors.toList());
        Map<Long, Long> articlePv = getArticlePvs(ids);
        for (BlogArticleBO blogArticleBO : blogArticleBOS) {
            blogArticleBO.setPv(articlePv.getOrDefault(blogArticleBO.getId(), 0L));
        }
    }

    private Map<Long, Long> getArticlePvs(Collection<Long> ids) {
        ResponseEntity<List<KeyValue<Long, Long>>> articlePvsResponse = remoteBlogArticleService.getPvByArticleId(ids, SecurityConstants.INNER);
        if (articlePvsResponse.isOk()) {
            return articlePvsResponse.getData().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1));
        }
        return Maps.newHashMap();
    }

    private void fillArticleTags(BlogArticleBO blogArticleBO) {
        List<BlogArticleTagBO> blogArticleTagBOS = blogRestArticleTagService.selectBlogArticleTagByArticleId(blogArticleBO.getId());
        List<Long> tagIds = blogArticleTagBOS.stream().map(BlogArticleTagBO::getTagId).collect(Collectors.toList());
        List<String> blogTagBOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tagIds)) {
            blogTagBOS = blogRestTagService.selectBlogTagByIds(tagIds).stream().map(BlogTagBO::getTitle).collect(Collectors.toList());
        }
        blogArticleBO.setBlogTags(blogTagBOS);
    }

    @Override
    public List<BlogArticleBO> selectBlogArticleList(BlogArticleBO blogArticleBO) {
        ResponseEntity<List<BlogArticleBO>> blogArticleListResponse = remoteBlogArticleService.selectBlogArticleList(BeanMapper.convert(blogArticleBO, BlogArticleDTO.class), SecurityConstants.INNER);
        Page<BlogArticleBO> blogArticleBOS = PageUtils.getInnerPage(blogArticleListResponse);
        fillPvInfo(blogArticleBOS);
        fillCommentCountInfo(blogArticleBOS);
        return blogArticleBOS;
    }

    @Override
    public Map<Long, Long> selectBlogArticleCountByCategoryIds(Set<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Maps.newHashMap();
        }
        ResponseEntity<List<KeyValue<Long, Long>>> keyValueListResponse = remoteBlogArticleService.selectBlogArticleCountByCategoryIds(categoryIds, SecurityConstants.INNER);
        if (keyValueListResponse.isOk()) {
            return keyValueListResponse.getData().stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1));
        }
        return Maps.newHashMap();
    }

    @Override
    public BlogArticlePrevNextBO selectPrevNextArticle(Long id) {
        ResponseEntity<List<BlogArticleBO>> blogArticleBOListResponse = remoteBlogArticleService.selectBlogArticlePrevNext(id, SecurityConstants.INNER);
        List<BlogArticleBO> blogArticles = Lists.newArrayList();
        if (blogArticleBOListResponse.isOk()) {
            blogArticles = blogArticleBOListResponse.getData();
        }
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
        ResponseEntity<List<BlogArticleBO>> blogArticleBORResponse = remoteBlogArticleService.selectBlogArticleByTagId(tagId, SecurityConstants.INNER);
        List<BlogArticleBO> blogArticles = Lists.newArrayList();
        if (blogArticleBORResponse.isOk()) {
            blogArticles = blogArticleBORResponse.getData();
        }
        List<BlogArticleBO> blogArticleBOS = BeanMapper.convertList(blogArticles, BlogArticleBO.class);
        fillCommentCountInfo(blogArticleBOS);
        return blogArticleBOS;
    }

    @Override
    public int likeArticle(Long id) {
        ResponseEntity<Integer> likeArticleResponse = remoteBlogArticleService.likeArticle(id, SecurityConstants.INNER);
        if (likeArticleResponse.isOk()) {
            return likeArticleResponse.getData();
        }
        return -1;
    }
}
