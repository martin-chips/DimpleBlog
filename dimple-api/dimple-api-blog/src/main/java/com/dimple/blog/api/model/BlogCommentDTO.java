package com.dimple.blog.api.model;

import com.dimple.common.core.web.page.PageDomain;
import lombok.Data;

@Data
public class BlogCommentDTO extends PageDomain {
    /**
     * comment article id
     */
    private Long articleId;
}
