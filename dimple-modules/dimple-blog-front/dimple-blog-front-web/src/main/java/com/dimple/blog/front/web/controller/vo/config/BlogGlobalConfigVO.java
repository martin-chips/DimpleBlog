package com.dimple.blog.front.web.controller.vo.config;

import lombok.Data;

/**
 * BlogConfig
 *
 * @author Dimple
 * @date 3/29/2023
 */
@Data
public class BlogGlobalConfigVO {
    private SiteConfig siteConfig;
    private CommentConfigVO commentConfig;
}
