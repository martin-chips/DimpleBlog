package com.dimple.blog.web.controller;

import com.dimple.blog.service.service.BlogConfigService;
import com.dimple.common.core.utils.StringUtils;
import com.dimple.common.core.web.controller.BaseController;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.common.security.annotation.InnerAuth;
import com.dimple.common.security.annotation.RequiresPermissions;
import com.dimple.system.api.model.config.BlogGlobalConfig;
import com.dimple.system.api.model.config.CommentConfig;
import com.dimple.system.api.model.config.EmailConfig;
import com.dimple.system.api.model.config.GithubLoginConfig;
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
    @RequiresPermissions("blog:config:edit")
    public AjaxResult updateConfig(@RequestBody BlogGlobalConfig blogGlobalConfig) {
        BlogGlobalConfig blogGlobalConfigInDb = blogConfigService.getBlogConfig();
        String emailPassword = Optional.ofNullable(blogGlobalConfig.getEmailConfig()).map(EmailConfig::getPassword).orElse("");
        if (StringUtils.isNotEmpty(emailPassword) && emailPassword.contains(MASK_STR)) {
            // means the password not update, so just using the db value
            blogGlobalConfig.getEmailConfig().setPassword(blogGlobalConfigInDb.getEmailConfig().getPassword());
        }
        String githubClientSecrets = Optional.ofNullable(blogGlobalConfig.getCommentConfig()).map(CommentConfig::getGithubLoginConfig).map(GithubLoginConfig::getClientSecrets).orElse("");
        if (StringUtils.isNotEmpty(githubClientSecrets) && githubClientSecrets.contains(MASK_STR)) {
            blogGlobalConfig.getCommentConfig().getGithubLoginConfig().setClientSecrets(blogGlobalConfigInDb.getCommentConfig().getGithubLoginConfig().getClientSecrets());
        }
        return success(blogConfigService.updateConfig(blogGlobalConfig));
    }

    @DeleteMapping
    @RequiresPermissions("blog:config:remove")
    public AjaxResult deleteConfigCache() {
        blogConfigService.deleteConfigCache();
        return success();
    }

    @GetMapping
    @RequiresPermissions("blog:config:query")
    public AjaxResult getBlogConfig() {
        BlogGlobalConfig blogGlobalConfig = blogConfigService.getBlogConfig();
        String githubClientSecrets = Optional.ofNullable(blogGlobalConfig.getCommentConfig()).map(CommentConfig::getGithubLoginConfig).map(GithubLoginConfig::getClientSecrets).orElse("");
        if (StringUtils.isNotEmpty(githubClientSecrets)) {
            GithubLoginConfig githubLoginConfig = blogGlobalConfig.getCommentConfig().getGithubLoginConfig();
            githubLoginConfig.setClientSecrets(githubLoginConfig.getClientSecrets().substring(0, 1) + MASK_STR);
        }
        String emailPassword = Optional.ofNullable(blogGlobalConfig.getEmailConfig()).map(EmailConfig::getPassword).orElse("");
        if (StringUtils.isNotEmpty(emailPassword)) {
            blogGlobalConfig.getEmailConfig().setPassword(MASK_STR);
        }
        return success(blogGlobalConfig);
    }

    @InnerAuth
    @GetMapping("global")
    public AjaxResult getGlobaclConfig() {
        return success(blogConfigService.getBlogConfig());
    }
}
