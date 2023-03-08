package com.dimple.blog.front.web.controller.vo;

import lombok.Data;

/**
 * BlogLinkVO
 *
 * @author Dimple
 * @date 3/8/2023 7:03 PM
 */
@Data
public class BlogLinkVO {
    private String title;
    private String headerImage;
    private String description;
    private String url;
    private Long id;
}
