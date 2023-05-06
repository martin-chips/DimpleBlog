package com.dimple.blog.service.service.impl;

import com.dimple.blog.api.bo.BlogArticleTagBO;
import com.dimple.blog.service.entity.BlogArticleTag;
import com.dimple.blog.service.mapper.BlogArticleTagMapper;
import com.dimple.blog.service.service.BlogArticleTagService;
import com.dimple.common.core.utils.bean.BeanMapper;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.apache.commons.collections4.CollectionUtils;
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

    @Override
    public int insertBlogArticleTag(BlogArticleTagBO blogArticleTagBO) {
        BlogArticleTag blogArticleTag = BeanMapper.convert(blogArticleTagBO, BlogArticleTag.class);
        return blogArticleTagMapper.insertBlogArticleTag(blogArticleTag);
    }

    @Override
    public int updateBlogArticleTag(BlogArticleTagBO blogArticleTagBO) {
        BlogArticleTag blogArticleTag = BeanMapper.convert(blogArticleTagBO, BlogArticleTag.class);
        return blogArticleTagMapper.updateBlogArticleTag(blogArticleTag);
    }

    @Override
    public int deleteBlogArticleTagByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return AjaxResult.AFFECTED_ROW_FAIL;
        }
        return blogArticleTagMapper.deleteBlogArticleTagByIds(ids);
    }

    @Override
    public int deleteBlogArticleTagById(Long id) {
        return blogArticleTagMapper.deleteBlogArticleTagById(id);
    }
}
