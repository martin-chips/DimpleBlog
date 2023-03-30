package com.dimple.blog.service.entity.config;

import lombok.Data;

/**
 * CommentConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class CommentConfig {
    private boolean enableLocalLogin;
    private boolean enableGithubLogin;
    private boolean enableQQLogin;
    private GithubLoginConfig githubLoginConfig;
}
