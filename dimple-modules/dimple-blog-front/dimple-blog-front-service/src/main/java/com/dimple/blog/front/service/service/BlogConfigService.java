package com.dimple.blog.front.service.service;


import com.dimple.system.api.model.config.BlogGlobalConfig;
import com.dimple.system.api.model.config.GithubLoginConfig;

/**
 * BlogConfigService
 *
 * @author Dimple
 * @date 3/29/2023
 */
public interface BlogConfigService {
    BlogGlobalConfig getBlogGlobalConfig();

    GithubLoginConfig getGithubLoginConfig();
}
