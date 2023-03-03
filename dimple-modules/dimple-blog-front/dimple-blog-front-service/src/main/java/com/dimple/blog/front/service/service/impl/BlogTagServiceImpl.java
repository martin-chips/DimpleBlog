package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogTag;
import com.dimple.blog.front.service.mapper.BlogTagMapper;
import com.dimple.blog.front.service.service.BlogTagService;
import com.dimple.blog.front.service.service.bo.BlogTagBO;
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
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagMapper blogTagMapper;

    @Override
    public BlogTagBO selectBlogTagById(Long id) {
        return BeanMapper.convert(blogTagMapper.selectBlogTagById(id), BlogTagBO.class);
    }

    @Override
    public List<BlogTagBO> selectBlogTagByIds(List<Long> ids) {
        List<BlogTag> blogTags = blogTagMapper.selectBlogTagListByIds(ids);
        return BeanMapper.convertList(blogTags, BlogTagBO.class);
    }

    @Override
    public List<BlogTagBO> selectBlogTagList(BlogTagBO blogTagBO) {
        BlogTag blogTag = BeanMapper.convert(blogTagBO, BlogTag.class);
        List<BlogTag> blogTags = blogTagMapper.selectBlogTagList(blogTag);
        return BeanMapper.convertList(blogTags, BlogTagBO.class);
    }

}
