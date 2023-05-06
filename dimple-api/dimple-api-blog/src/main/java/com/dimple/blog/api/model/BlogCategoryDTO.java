package com.dimple.blog.api.model;

import com.dimple.common.core.web.page.PageDomain;
import lombok.Data;

@Data
public class BlogCategoryDTO extends PageDomain {
    private Long id;

    /**
     * category title, max length is 255
     */
    private String title;
}
