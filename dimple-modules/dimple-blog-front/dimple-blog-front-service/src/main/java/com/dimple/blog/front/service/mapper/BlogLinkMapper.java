package com.dimple.blog.front.service.mapper;

import com.dimple.blog.front.service.entity.BlogLink;

import java.util.List;

/**
 * BlogLinkMapper
 *
 * @author Dimple
 * @date 3/8/2023 7:58 PM
 */
public interface BlogLinkMapper {
    int insertBlogLink(BlogLink blogLink);

    List<BlogLink> selectBlogLinkList(BlogLink blogLink);

    int addLinkVisitCount(Long id);
}
