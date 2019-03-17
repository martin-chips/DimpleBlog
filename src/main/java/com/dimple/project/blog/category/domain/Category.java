package com.dimple.project.blog.category.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: Category
 * @description: 分类
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.1
 */
@Data
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer categoryId;

    private String categoryTitle;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 描述
     */
    private String description;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}