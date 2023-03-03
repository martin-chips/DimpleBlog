package com.dimple.blog.front.web.controller.vo;

import lombok.Data;

/**
 * BlogCategoryVOParams
 *
 * @author Dimple
 */
@Data
public class BlogCategoryVO {
    private Long id;
    private String title;
    private Long articleCount;
}
