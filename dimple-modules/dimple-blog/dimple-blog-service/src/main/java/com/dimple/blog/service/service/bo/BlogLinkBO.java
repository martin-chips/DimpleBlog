package com.dimple.blog.service.service.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * BlogLinkBO
 *
 * @author Dimple
 * @date 3/10/2023 11:26 AM
 */
@Data
public class BlogLinkBO extends BaseEntity {
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
