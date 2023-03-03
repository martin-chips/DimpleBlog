package com.dimple.blog.front.web.controller.vo;

import lombok.Data;

/**
 * BlogTagVO
 *
 * @author Dimple
 * @date 2/14/2023 10:23 AM
 */
@Data
public class BlogTagVO {
    private Long id;

    /**
     * the article tag
     */
    private String title;
}
