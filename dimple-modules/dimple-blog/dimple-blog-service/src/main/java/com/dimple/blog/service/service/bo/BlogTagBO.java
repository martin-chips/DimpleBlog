package com.dimple.blog.service.service.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;


/**
 * 对象 blog_tag
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogTagBO extends BaseEntity {


    private Long id;

    /**
     * the article tag
     */
    private String title;

}
