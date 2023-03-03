package com.dimple.blog.front.web.controller.vo.params;

import com.dimple.common.core.web.vo.params.BaseVOParams;
import lombok.Data;

/**
 * BlogArchivesVOParams
 *
 * @author Dimple
 * @date 2/28/2023 11:10 AM
 */
@Data
public class BlogArchivesVOParams extends BaseVOParams {
    /**
     * 分类类型
     */
    private String countType;
}
