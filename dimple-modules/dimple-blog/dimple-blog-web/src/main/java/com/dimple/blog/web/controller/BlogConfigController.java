package com.dimple.blog.web.controller;

import com.dimple.blog.service.entity.config.BlogConfig;
import com.dimple.blog.service.entity.config.CommentConfig;
import com.dimple.blog.service.entity.config.EmailConfig;
import com.dimple.blog.service.entity.config.GithubLoginConfig;
import com.dimple.blog.service.service.BlogConfigService;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * BlogConfigController
 *
 * @author Dimple
 * @date 3/30/2023
 */
@RestController
@RequestMapping("/config")
public class BlogConfigController extends BaseController {
    @Autowired
    private BlogConfigService blogConfigService;
    private static final String MASK_STR = "***********";

    @PutMapping
    public AjaxResult updateConfig(@RequestBody BlogConfig blogConfig) {
        BlogConfig blogConfigInDb = blogConfigService.getBlogConfig();
        String emailPassword = Optional.ofNullable(blogConfig.getEmailConfig()).map(EmailConfig::getPassword).orElse("");
        if (StringUtils.isNotEmpty(emailPassword) && emailPassword.contains(MASK_STR)) {
            // means the password not update, so just using the db value
            blogConfig.getEmailConfig().setPassword(blogConfigInDb.getEmailConfig().getPassword());
        }
        String githubClientSecrets = Optional.ofNullable(blogConfig.getCommentConfig()).map(CommentConfig::getGithubLoginConfig).map(GithubLoginConfig::getClientSecrets).orElse("");
        if (StringUtils.isNotEmpty(githubClientSecrets) && githubClientSecrets.contains(MASK_STR)) {
            blogConfig.getCommentConfig().getGithubLoginConfig().setClientSecrets(blogConfigInDb.getCommentConfig().getGithubLoginConfig().getClientSecrets());
        }
        return success(blogConfigService.updateConfig(blogConfig));
    }

    @DeleteMapping
    public AjaxResult deleteConfigCache() {
        blogConfigService.deleteConfigCache();
        return success();
    }

    @GetMapping
    public AjaxResult getBlogConfig() {
        BlogConfig blogConfig = blogConfigService.getBlogConfig();
        String githubClientSecrets = Optional.ofNullable(blogConfig.getCommentConfig()).map(CommentConfig::getGithubLoginConfig).map(GithubLoginConfig::getClientSecrets).orElse("");
        if (StringUtils.isNotEmpty(githubClientSecrets)) {
            GithubLoginConfig githubLoginConfig = blogConfig.getCommentConfig().getGithubLoginConfig();
            githubLoginConfig.setClientSecrets(githubLoginConfig.getClientSecrets().substring(0, 1) + MASK_STR);
        }
        String emailPassword = Optional.ofNullable(blogConfig.getEmailConfig()).map(EmailConfig::getPassword).orElse("");
        if (StringUtils.isNotEmpty(emailPassword)) {
            blogConfig.getEmailConfig().setPassword(MASK_STR);
        }
        return success(blogConfig);
    }
}
