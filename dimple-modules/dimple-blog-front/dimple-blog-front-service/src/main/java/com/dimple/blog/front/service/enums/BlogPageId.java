package com.dimple.blog.front.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * BlogPageId
 *
 * @author BianXiaofeng
 * @date 2023/3/16
 */
@AllArgsConstructor
@Getter
public enum BlogPageId {
    ABOUT(-2000L),
    MESSAGE_BOARD(-1000L),
    ;
    private Long id;
}
