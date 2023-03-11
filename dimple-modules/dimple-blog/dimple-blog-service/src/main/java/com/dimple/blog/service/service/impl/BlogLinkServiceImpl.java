package com.dimple.blog.service.service.impl;

import com.dimple.blog.service.entity.BlogLink;
import com.dimple.blog.service.mapper.BlogLinkMapper;
import com.dimple.blog.service.service.BlogLinkService;
import com.dimple.blog.service.service.bo.BlogLinkBO;
import com.dimple.common.core.utils.DateUtils;
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
public class BlogLinkServiceImpl implements BlogLinkService {
    @Autowired
    private BlogLinkMapper blogLinkMapper;

    @Override
    public BlogLinkBO selectBlogLinkById(Long id) {
        BlogLinkBO blogLinkBO = BeanMapper.convert(blogLinkMapper.selectBlogLinkById(id), BlogLinkBO.class);
        return blogLinkBO;
    }

    @Override
    public List<BlogLinkBO> selectBlogLinkList(BlogLinkBO blogLinkBO) {
        BlogLink blogLink = BeanMapper.convert(blogLinkBO, BlogLink.class);
        List<BlogLink> blogLinks = blogLinkMapper.selectBlogLinkList(blogLink);
        List<BlogLinkBO> blogLinkBOS = BeanMapper.convertList(blogLinks, BlogLinkBO.class);
        return blogLinkBOS;
    }


    @Override
    public int insertBlogLink(BlogLinkBO blogLinkBO) {
        BlogLink blogLink = BeanMapper.convert(blogLinkBO, BlogLink.class);
        blogLink.setCreateTime(DateUtils.getNowDate());
        return blogLinkMapper.insertBlogLink(blogLink);
    }

    @Override
    public int updateBlogLink(BlogLinkBO blogLinkBO) {
        BlogLink blogLink = BeanMapper.convert(blogLinkBO, BlogLink.class);
        blogLink.setUpdateTime(DateUtils.getNowDate());
        return blogLinkMapper.updateBlogLink(blogLink);
    }

    @Override
    public int deleteBlogLinkByIds(Long[] ids) {
        return blogLinkMapper.deleteBlogLinkByIds(ids);
    }

    @Override
    public int deleteBlogLinkById(Long id) {
        return blogLinkMapper.deleteBlogLinkById(id);
    }
}
