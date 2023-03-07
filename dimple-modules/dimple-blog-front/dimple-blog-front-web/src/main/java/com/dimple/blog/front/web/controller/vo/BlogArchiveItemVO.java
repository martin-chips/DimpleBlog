package com.dimple.blog.front.web.controller.vo;

import lombok.Data;

import java.util.Date;

/**
 * BlogArchiveItemVO
 *
 * @author BianXiaofeng
 * @date 3/7/2023 10:13 AM
 */
@Data
public class BlogArchiveItemVO {
    private Long id;
    private String title;
    private String headerImage;
    private Date createTime;
}
