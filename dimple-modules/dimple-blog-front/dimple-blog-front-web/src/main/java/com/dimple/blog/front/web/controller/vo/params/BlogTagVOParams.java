package com.dimple.blog.front.web.controller.vo.params;

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

    /**
     * the article tag
     */
    private String title;
}
