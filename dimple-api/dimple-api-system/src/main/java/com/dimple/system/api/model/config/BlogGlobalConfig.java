package com.dimple.system.api.model.config;

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
