package com.dimple.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ArticleStatus
 *
 * @author Dimple
 */
@AllArgsConstructor
@Getter
public enum BlogArticleStatus {
    /**
     * 1 published
     */
    PUBLISHED(1),

    /**
     * 2 draft
     */
    DRAFT(2),
    ;
    private Integer status;
}
