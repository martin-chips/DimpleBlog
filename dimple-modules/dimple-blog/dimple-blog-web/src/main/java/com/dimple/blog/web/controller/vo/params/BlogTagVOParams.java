package com.dimple.blog.web.controller.vo.params;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * BlogTagVOParams
 *
 * @author Dimple
 * @date 2/14/2023 10:23 AM
 */
@Data
public class BlogTagVOParams extends BaseVOParams {
    private Long id;

    /**
     * the article tag
     */
    private String title;
}
