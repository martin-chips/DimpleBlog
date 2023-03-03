package com.dimple.blog.front.service.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BlogArticlePrevNextVO
 *
 * @author Dimple
 * @date 2/28/2023 1:42 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticlePrevNextBO {
    private BlogArticlePreNextItemBO prev;
    private BlogArticlePreNextItemBO next;

}
