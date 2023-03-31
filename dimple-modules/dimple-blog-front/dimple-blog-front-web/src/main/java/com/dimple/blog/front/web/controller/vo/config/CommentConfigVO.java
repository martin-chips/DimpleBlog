package com.dimple.blog.front.web.controller.vo.config;

import lombok.Data;

/**
 * CommentConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class CommentConfigVO {
    private boolean enableLocalLogin;
    private boolean enableGithubLogin;
    private boolean enableQQLogin;
    private GithubLoginConfig githubLoginConfig;
}
