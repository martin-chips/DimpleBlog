package com.dimple.blog.service.entity;

import lombok.Data;

import java.util.Date;

/**
 * BlogArchiveItem
 *
 * @author Dimple
 * @date 3/7/2023 10:01 AM
 */
@Data
public class BlogArchiveItem {
    private Long id;
    private String title;
    private String headerImage;
    private Date createTime;
}
