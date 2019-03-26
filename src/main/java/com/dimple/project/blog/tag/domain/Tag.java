package com.dimple.project.blog.tag.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: Tag
 * @description: 标签
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.1
 */
@Data
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Integer tagId;

    /**
     * 标签内容
     */
    private String tagTitle;
    /**
     * 关联的博客个数
     */
    private int count;
}