package com.dimple.blog.front.web.controller.vo;

import lombok.Data;

/**
 * BlogArticlePrevNextVO
 *
 * @author Dimple
 * @date 2/28/2023 1:42 PM
 */
@Data
public class BlogArticlePrevNextVO {
    private BlogArticlePreNextItemVO prev;
    private BlogArticlePreNextItemVO next;

}
