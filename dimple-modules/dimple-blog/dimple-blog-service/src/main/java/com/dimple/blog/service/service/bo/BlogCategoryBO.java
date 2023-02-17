package com.dimple.blog.service.service.bo;

import com.dimple.common.core.annotation.Excel;
import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.List;


/**
 * blog category对象 blog_category
 *
 * @author Dimple
 * @date 2023-02-13
 */
@Data
public class BlogCategoryBO extends BaseEntity {


    private Long id;

    /**
     * category title, max length is 255
     */
    @Excel(name = "标题")
    private String title;

    private List<BlogArticleBO> articles;

}
