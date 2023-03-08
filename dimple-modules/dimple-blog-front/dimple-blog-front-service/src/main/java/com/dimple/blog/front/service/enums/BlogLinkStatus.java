package com.dimple.blog.front.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BlogLinkStatus
 *
 * @author Dimple
 * @date 3/8/2023 7:56 PM
 */
@AllArgsConstructor
@Getter
public enum BlogLinkStatus {
    PENDING(1),
    REJECTED(2),
    APPROVE(3);

    private Integer status;

}
