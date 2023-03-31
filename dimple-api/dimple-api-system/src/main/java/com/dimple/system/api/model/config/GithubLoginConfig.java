package com.dimple.system.api.model.config;

import lombok.Data;

/**
 * GithubLoginConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class GithubLoginConfig {
    private String adminId;
    private String clientId;
    private String clientSecrets;
    private String accessTokenUrl;
    private String loginUrl;
    private String userInfoUrl;
}
