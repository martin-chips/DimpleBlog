package com.dimple.blog.api.bo.config;

import lombok.Data;

/**
 * BlogConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class BlogGlobalConfig {
    private SiteConfig siteConfig;
    private CommentConfig commentConfig;
    private EmailConfig emailConfig;
}
