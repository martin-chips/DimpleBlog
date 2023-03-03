package com.dimple.blog.front.service.entity;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;


/**
 * blog category对象 blog_category
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogCategory extends BaseEntity {


    private Long id;

    /**
     * category title, max length is 255
     */
    private String title;


}
