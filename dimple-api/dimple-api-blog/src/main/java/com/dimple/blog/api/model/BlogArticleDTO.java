package com.dimple.blog.api.model;

import com.dimple.common.core.web.page.PageDomain;
import lombok.Data;

@Data
public class BlogArticleDTO extends PageDomain {
    private Long id;

    /**
     * article title
     */
    private String title;

    private Long categoryId;

    private String summary;
}
