package com.dimple.blog.api.bo;

import com.dimple.common.core.annotation.Excel;
import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 对象 blog_tag
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
@NoArgsConstructor
public class BlogTagBO extends BaseEntity {


    private Long id;

    /**
     * the article tag
     */
    @Excel(name = "标题")
    private String title;

    public BlogTagBO(String title) {
        this.title = title;
    }
}
