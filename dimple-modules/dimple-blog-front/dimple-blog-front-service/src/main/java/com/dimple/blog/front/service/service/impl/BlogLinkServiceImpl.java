package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.entity.BlogLink;
import com.dimple.blog.front.service.mapper.BlogLinkMapper;
import com.dimple.blog.front.service.service.BlogLinkService;
import com.dimple.blog.front.service.service.bo.BlogLinkBO;
import com.dimple.common.core.enums.BlogLinkStatus;
import com.dimple.common.core.utils.bean.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BlogLinkServiceImpl
 *
 * @author Dimple
 * @date 3/8/2023 7:55 PM
 */
@Service
public class BlogLinkServiceImpl implements BlogLinkService {
    @Autowired
    private BlogLinkMapper blogLinkMapper;

    @Override
    public int insertLink(BlogLinkBO blogLinkBO) {
        BlogLink blogLink = BeanMapper.convert(blogLinkBO, BlogLink.class);
        blogLink.setLinkStatus(BlogLinkStatus.PENDING.getStatus());
        return blogLinkMapper.insertBlogLink(blogLink);
    }

    @Override
    public List<BlogLinkBO> listBlogLink(BlogLinkBO blogLinkBO) {
        BlogLink blogLink = BeanMapper.convert(blogLinkBO, BlogLink.class);
        List<BlogLink> blogLinks = blogLinkMapper.selectBlogLinkList(blogLink);
        return BeanMapper.convertList(blogLinks, BlogLinkBO.class);
    }

    @Override
    public int addLinkVisitCount(Long id) {
        return blogLinkMapper.addLinkVisitCount(id);
    }
}
