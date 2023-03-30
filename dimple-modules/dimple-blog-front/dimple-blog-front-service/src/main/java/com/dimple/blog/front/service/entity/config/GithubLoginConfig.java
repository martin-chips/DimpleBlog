package com.dimple.blog.front.service.entity.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * GithubLoginConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class GithubLoginConfig {
    @JsonIgnore
    private String adminId;
    private String clientId;
    @JsonIgnore
    private String clientSecrets;
    private String accessTokenUrl;
    private String loginUrl;
    private String userInfoUrl;
}
