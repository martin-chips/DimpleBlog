package com.dimple.blog.front.service.service.impl;

import com.dimple.blog.front.service.service.BlogConfigService;
import com.dimple.common.core.constant.SecurityConstants;
import com.dimple.common.core.domain.ResponseEntity;
import com.dimple.common.redis.constants.CacheConstants;
import com.dimple.common.redis.core.RedisKeyDefine;
import com.dimple.common.redis.service.RedisService;
import com.dimple.system.api.RemoteBlogService;
import com.dimple.system.api.model.config.BlogGlobalConfig;
import com.dimple.system.api.model.config.GithubLoginConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        BlogGlobalConfig blogGlobalConfig = redisService.getCacheObject(redisKeyDefine.getKeyTemplate());
        if (!Objects.isNull(blogGlobalConfig)) {
            return blogGlobalConfig;
        }
        ResponseEntity<BlogGlobalConfig> globalConfigResponseEntity = remoteBlogService.getBlogGlobalConfig(SecurityConstants.INNER);
        if (globalConfigResponseEntity == null || globalConfigResponseEntity.getCode() != 200) {
            log.error("Call remote blog service failed.{}", globalConfigResponseEntity);
            return null;
        }
        blogGlobalConfig = globalConfigResponseEntity.getData();
        redisService.setCacheObject(redisKeyDefine.getKeyTemplate(), blogGlobalConfig);
        return blogGlobalConfig;

    }

    @Override
    public GithubLoginConfig getGithubLoginConfig() {
        BlogGlobalConfig blogGlobalConfig = this.getBlogGlobalConfig();
        GithubLoginConfig githubLoginConfig = Optional.ofNullable(blogGlobalConfig.getCommentConfig()).map(e -> e.getGithubLoginConfig()).orElse(null);
        return githubLoginConfig;
    }
}
