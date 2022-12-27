package com.dimple.blog.service.entity;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * blog category
 *
 * @author Dimple
 */
@Data
public class Category extends BaseVOParams {
    private Integer id;
    private String title;
}
