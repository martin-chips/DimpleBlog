package com.dimple.project.blog.category.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

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
    /**
     * 分类的名称
     */
    private String categoryTitle;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 描述
     */
    private String description;
    /**
     * 是否推荐，0表示不推荐，1表示推荐
     */
    private String support;
}