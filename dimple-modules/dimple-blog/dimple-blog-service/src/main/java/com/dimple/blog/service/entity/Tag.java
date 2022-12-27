package com.dimple.blog.service.entity;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * blog tag
 *
 * @author Dimple
 */
@Data
public class Tag extends BaseVOParams {
    private Integer id;

    private String title;
}
