package com.dimple.blog.front.service.service;

import com.dimple.blog.front.service.entity.config.BlogConfig;
import com.dimple.blog.front.service.entity.config.GithubLoginConfig;

/**
 * BlogConfigService
 *
 * @author Dimple
 * @date 3/29/2023
 */
public interface BlogConfigService {
    BlogConfig getBlogConfig();

    GithubLoginConfig getGithubLoginConfig();
}
