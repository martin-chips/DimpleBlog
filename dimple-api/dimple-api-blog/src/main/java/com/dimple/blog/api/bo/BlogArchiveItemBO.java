package com.dimple.blog.api.bo;

import lombok.Data;

import java.util.Date;

/**
 * BlogArchiveItem
 *
 * @author Dimple
 * @date 3/7/2023 10:00 AM
 */
@Data
public class BlogArchiveItemBO {
    private Long id;
    private String title;
    private String headerImage;
    private Date createTime;
}
