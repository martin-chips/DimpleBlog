package com.dimple.blog.front.service.service;

import com.dimple.blog.api.bo.BlogLinkBO;

import java.util.List;

/**
 * BlogRestLinkService
 *
 * @author Dimple
 * @date 3/8/2023 7:54 PM
 */
public interface BlogRestLinkService {
    int insertLink(BlogLinkBO blogLinkBO);

    List<BlogLinkBO> listBlogLink(BlogLinkBO blogLinkBO);

    int addLinkVisitCount(Long id);
}
