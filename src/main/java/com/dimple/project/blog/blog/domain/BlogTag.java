package com.dimple.project.blog.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: BlogTag
 * @description: 博客和标签
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.1
 */
@Data
public class BlogTag extends BaseEntity {
    private Integer blogId;
    private Integer tagId;

}