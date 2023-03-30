package com.dimple.blog.service.entity.config;

import lombok.Data;

/**
 * SiteConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class SiteConfig {
    private boolean mask;
    private String infoPanel;
    private String siteName;
    private String avatars;
    private String icp;
    private String githubUrl;
    private String copyright;
    private String githubUserName;
}
