package com.dimple.blog.service.entity;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * Blog reading count
 *
 * @author Dimple
 */
@Data
public class ArticleReading extends BaseVOParams {
    private Integer id;

    private Integer blogId;

}
