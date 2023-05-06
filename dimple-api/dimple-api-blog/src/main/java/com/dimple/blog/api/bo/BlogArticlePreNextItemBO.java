package com.dimple.blog.api.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BlogArticlePreNextItemVO
 *
 * @author Dimple
 * @date 2/28/2023 1:43 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticlePreNextItemBO {
    private Long id;
    private String headImage;
    private String title;
}
