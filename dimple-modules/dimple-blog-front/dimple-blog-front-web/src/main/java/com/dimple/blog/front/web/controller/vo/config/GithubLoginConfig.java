package com.dimple.blog.front.web.controller.vo.config;

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
    private String loginUrl;
}
