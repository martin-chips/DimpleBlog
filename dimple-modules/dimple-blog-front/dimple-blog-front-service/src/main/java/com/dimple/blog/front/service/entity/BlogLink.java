package com.dimple.blog.front.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * BlogLink
 *
 * @author Dimple
 * @date 3/8/2023 7:39 PM
 */
@Data
public class BlogLink extends BaseEntity {
    private Long id;
    private String title;
    private String headerImage;
    private String description;
    private String url;
    private Integer linkStatus;
    private Long visitCount;
    private String email;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
