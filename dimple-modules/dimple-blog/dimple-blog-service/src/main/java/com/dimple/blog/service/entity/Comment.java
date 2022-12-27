package com.dimple.blog.service.entity;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * blog comment
 *
 * @author Dimple
 */
@Data
public class Comment extends BaseVOParams {
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
