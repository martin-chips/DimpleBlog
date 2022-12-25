package com.dimple.blog.domain;

import com.dimple.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * blog comment
 *
 * @author Dimple
 */
@Data
public class Comment extends BaseEntity {
    private String id;

    private Integer articleId;

    private String username;

    /**
     * private comment id, default is 0
     */
    private Integer parentId;

    private String headImage;

    private String content;

    private String email;

}
