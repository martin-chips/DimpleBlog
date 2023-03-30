package com.dimple.blog.front.service.config;

import lombok.Data;

/**
 * GithubTokenInfo
 *
 * @author Dimple
 * @date 2023/3/14 13:42
 */
@Data
public class GithubTokenInfoConfig {
    private String clientId;
    private String clientSecrets;
    private String accessTokenUrl;
    private String userInfoUrl;
    private Long adminId;
}

