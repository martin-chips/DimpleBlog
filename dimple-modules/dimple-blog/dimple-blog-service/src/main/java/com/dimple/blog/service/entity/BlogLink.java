package com.dimple.blog.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * BlogLink
 *
 * @author BianXiaofeng
 * @date 3/10/2023 11:26 AM
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
