package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogArticleTag;
import com.dimple.blog.front.service.mapper.BlogArticleTagMapper;
import com.dimple.blog.front.service.service.BlogArticleTagService;
import com.dimple.blog.front.service.service.bo.BlogArticleTagBO;
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
public class BlogArticleTagServiceImpl implements BlogArticleTagService {
    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    @Override
    public BlogArticleTagBO selectBlogArticleTagById(Long id) {
        return BeanMapper.convert(blogArticleTagMapper.selectBlogArticleTagById(id), BlogArticleTagBO.class);
    }

    @Override
    public List<BlogArticleTagBO> selectBlogArticleTagByArticleId(Long articleId) {
        List<BlogArticleTag> blogArticleTags = blogArticleTagMapper.selectBlogArticleTagByArticleId(articleId);
        return BeanMapper.convertList(blogArticleTags, BlogArticleTagBO.class);
    }

    @Override
    public List<BlogArticleTagBO> selectBlogArticleTagList(BlogArticleTagBO blogArticleTagBO) {
        BlogArticleTag blogArticleTag = BeanMapper.convert(blogArticleTagBO, BlogArticleTag.class);
        List<BlogArticleTag> blogArticleTags = blogArticleTagMapper.selectBlogArticleTagList(blogArticleTag);
        return BeanMapper.convertList(blogArticleTags, BlogArticleTagBO.class);
    }

}
