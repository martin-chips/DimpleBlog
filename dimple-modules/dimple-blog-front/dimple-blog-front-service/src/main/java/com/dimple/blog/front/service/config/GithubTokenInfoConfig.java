package com.dimple.blog.front.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * GithubTokenInfo
 *
 * @author Dimple
 * @date 2023/3/14 13:42
 */
@ConfigurationProperties(prefix = "github.oauth")
@Data
@Component
public class GithubTokenInfoConfig {
    private String clientId;
    private String clientSecrets;
    private String accessTokenUrl;
    private String userInfoUrl;
    private Long adminId;
}

