package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.service.bo.BlogLinkBO;

import java.util.List;

/**
 * BlogLinkService
 *
 * @author Dimple
 * @date 3/8/2023 7:54 PM
 */
public interface BlogLinkService  {
    int insertLink(BlogLinkBO blogLinkBO);

    List<BlogLinkBO> listBlogLink(BlogLinkBO blogLinkBO);

    int addLinkVisitCount(Long id);
}
