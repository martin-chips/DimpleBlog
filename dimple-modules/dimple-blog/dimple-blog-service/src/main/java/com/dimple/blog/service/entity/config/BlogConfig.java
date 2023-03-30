package com.dimple.blog.service.entity.config;

import lombok.Data;

/**
 * BlogConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class BlogConfig {
    private SiteConfig siteConfig;
    private CommentConfig commentConfig;
    private EmailConfig emailConfig;
}
