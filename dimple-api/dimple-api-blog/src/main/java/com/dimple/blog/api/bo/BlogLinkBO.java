package com.dimple.blog.api.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * BlogLinkBO
 *
 * @author Dimple
 * @date 3/8/2023 7:55 PM
 */
@Data
public class BlogLinkBO extends BaseEntity {
    private Long id;
    private String title;
    private String headerImage;
    private String description;
    private String url;
    private String email;
    private Integer linkStatus;
    private Long visitCount;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String emailContent;
}
