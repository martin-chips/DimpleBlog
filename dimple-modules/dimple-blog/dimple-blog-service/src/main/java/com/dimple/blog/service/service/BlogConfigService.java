package com.dimple.blog.service.service;

import com.dimple.blog.service.entity.config.BlogConfig;

/**
 * BlogConfigService
 *
 * @author Dimple
 * @date 3/30/2023
 */
public interface BlogConfigService {
    int updateConfig(BlogConfig blogConfig);

    void deleteConfigCache();

    BlogConfig getBlogConfig();


}
