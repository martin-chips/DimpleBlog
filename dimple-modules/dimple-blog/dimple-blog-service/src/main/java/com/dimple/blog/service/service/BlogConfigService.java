package com.dimple.blog.service.service;

import com.dimple.system.api.model.config.BlogGlobalConfig;

/**
 * BlogConfigService
 *
 * @author Dimple
 * @date 3/30/2023
 */
public interface BlogConfigService {
    int updateConfig(BlogGlobalConfig blogGlobalConfig);

    void deleteConfigCache();

    BlogGlobalConfig getBlogConfig();


}
