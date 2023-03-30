package com.dimple.blog.service.service.impl;

import com.alibaba.fastjson2.JSON;
import com.dimple.blog.service.entity.config.BlogConfig;
import com.dimple.blog.service.mapper.BlogConfigMapper;
import com.dimple.blog.service.service.BlogConfigService;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BlogConfigServiceImpl
 *
 * @author Dimple
 * @date 3/30/2023
 */
@Service
public class BlogConfigServiceImpl implements BlogConfigService {
    @Autowired
    private BlogConfigMapper blogConfigMapper;
    @Autowired
    private RedisService redisService;
    private RedisKeyDefine redisKeyDefine = CacheConstants.BLOG_FRONT_CONFIG_KEY_DEFINE;

    @Override
    public int updateConfig(BlogConfig blogConfig) {
        String jsonString = JSON.toJSONString(blogConfig);
        return blogConfigMapper.updateConfig(jsonString);
    }

    @Override
    public void deleteConfigCache() {
        redisService.deleteObject(redisKeyDefine.getKeyTemplate());
    }

    @Override
    public BlogConfig getBlogConfig() {
        String config = blogConfigMapper.getConfig();
        return JSON.parseObject(config, BlogConfig.class);
    }
}
