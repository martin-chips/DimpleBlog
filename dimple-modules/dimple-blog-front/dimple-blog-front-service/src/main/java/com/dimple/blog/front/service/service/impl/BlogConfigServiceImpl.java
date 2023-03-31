package com.dimple.blog.front.service.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.dimple.blog.front.service.service.BlogConfigService;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.service.RedisService;
import com.dimple.system.api.RemoteBlogService;
import com.dimple.system.api.model.config.BlogGlobalConfig;
import com.dimple.system.api.model.config.GithubLoginConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * BlogConfigServiceImpl
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Service
@Slf4j
public class BlogConfigServiceImpl implements BlogConfigService {
    @Autowired
    private RemoteBlogService remoteBlogService;
    @Autowired
    private RedisService redisService;
    private RedisKeyDefine redisKeyDefine = CacheConstants.BLOG_FRONT_CONFIG_KEY_DEFINE;

    @Override
    public BlogGlobalConfig getBlogGlobalConfig() {
        String jsonStr = redisService.getCacheObject(redisKeyDefine.getKeyTemplate());
        if (StringUtils.isNotEmpty(jsonStr)) {
            return JSONObject.parseObject(jsonStr, BlogGlobalConfig.class);
        }
        ResponseEntity<BlogGlobalConfig> globalConfigResponseEntity = remoteBlogService.getBlogGlobalConfig();
        if (globalConfigResponseEntity == null || globalConfigResponseEntity.getCode() != 200) {
            log.error("Call remote blog service failed.");
            return null;
        }
        BlogGlobalConfig responseEntityData = globalConfigResponseEntity.getData();
        redisService.setCacheObject(redisKeyDefine.getKeyTemplate(), responseEntityData);
        return responseEntityData;

    }

    @Override
    public GithubLoginConfig getGithubLoginConfig() {
        BlogGlobalConfig blogGlobalConfig = this.getBlogGlobalConfig();
        GithubLoginConfig githubLoginConfig = Optional.ofNullable(blogGlobalConfig.getCommentConfig()).map(e -> e.getGithubLoginConfig()).orElse(null);
        return githubLoginConfig;
    }
}
