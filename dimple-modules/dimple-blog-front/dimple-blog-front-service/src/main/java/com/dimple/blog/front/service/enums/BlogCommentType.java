package com.dimple.blog.front.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BlogCommentType
 *
 * @author Dimple
 * @date 2023/3/15
 */
@AllArgsConstructor
@Getter
public enum BlogCommentType {
    LOCAL(0),
    GITHUB(1);
    private Integer type;
}
