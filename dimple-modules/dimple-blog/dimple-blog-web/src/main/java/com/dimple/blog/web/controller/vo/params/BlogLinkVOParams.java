package com.dimple.blog.web.controller.vo.params;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * BlogLinkVOParams
 *
 * @author Dimple
 * @date 3/10/2023 11:27 AM
 */
@Data
public class BlogLinkVOParams extends BaseVOParams {
    private String title;
    private String headerImage;
    private String description;
    private String url;
    private Integer linkStatus;
    private Long visitCount;
    private String email;
}
