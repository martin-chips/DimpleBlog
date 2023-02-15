package com.dimple.blog.web.controller.vo;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * BlogCategoryVOParams
 *
 * @author Dimple
 */
@Data
public class BlogCategoryVO extends BaseVOParams {
    private Long id;
    private String title;
}
