package com.dimple.blog.front.service.service;


import com.dimple.blog.api.bo.config.BlogGlobalConfig;
import com.dimple.blog.api.bo.config.GithubLoginConfig;

/**
 * BlogRestConfigService
 *
 * @author Dimple
 * @date 3/29/2023
 */
public interface BlogRestConfigService {
    BlogGlobalConfig getBlogGlobalConfig();

    GithubLoginConfig getGithubLoginConfig();
}
