package com.dimple.blog.front.service.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.dimple.blog.front.service.entity.config.BlogConfig;
import com.dimple.blog.front.service.mapper.BlogConfigMapper;
import com.dimple.blog.front.service.service.BlogConfigService;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BlogConfigServiceImpl
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Service
public class BlogConfigServiceImpl implements BlogConfigService {
    @Autowired
    private BlogConfigMapper blogConfigMapper;
    @Autowired
    private RedisService redisService;
    private RedisKeyDefine redisKeyDefine = CacheConstants.BLOG_FRONT_CONFIG_KEY_DEFINE;

    @Override
    public BlogConfig getBlogConfig() {
        String jsonStr = redisService.getCacheObject(redisKeyDefine.getKeyTemplate());
        if (StringUtils.isEmpty(jsonStr)) {
            jsonStr = blogConfigMapper.getBlogConfig();
        }
        return JSONObject.parseObject(jsonStr, BlogConfig.class);
    }
}
