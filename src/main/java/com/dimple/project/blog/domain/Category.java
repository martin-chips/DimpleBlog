package com.dimple.project.blog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Category
 * @description: 分类对象 bg_category
 * @author: Dimple
 * @date: 2019-10-28
 */
@Data
@NoArgsConstructor
public class Category extends BaseEntity {

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 分类名称
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否推荐
     */
    private Integer support;

}
