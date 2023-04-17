package com.dimple.system.api.model.config;

import lombok.Data;

import java.util.List;

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
    private List<List<String>> lables;
}
