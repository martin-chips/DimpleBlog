package com.dimple.common.core.enums;

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
    GITHUB(1),
    QQ(2),;
    private Integer type;
}
