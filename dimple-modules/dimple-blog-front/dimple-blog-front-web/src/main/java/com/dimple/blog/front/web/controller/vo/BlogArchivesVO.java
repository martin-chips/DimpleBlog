package com.dimple.blog.front.web.controller.vo;

import com.dimple.blog.api.bo.BlogArchiveItemBO;
import lombok.Data;

import java.util.List;

/**
 * ArchivesVO
 *
 * @author Dimple
 * @date 2/28/2023 11:07 AM
 */
@Data
public class BlogArchivesVO {
    private Long count;

    private String dateStr;

    List<BlogArchiveItemBO> items;
}
