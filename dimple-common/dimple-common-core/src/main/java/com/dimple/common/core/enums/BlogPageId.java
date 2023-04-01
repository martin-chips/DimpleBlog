package com.dimple.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BlogPageId
 *
 * @author Dimple
 * @date 2023/3/16
 */
@AllArgsConstructor
@Getter
public enum BlogPageId {
    ABOUT(-2000L, "关于我"),
    MESSAGE_BOARD(-1000L, "留言板"),
    ;
    private Long id;
    private String desc;

    public static String getPageDesc(Long id) {
        return Arrays.stream(values()).filter(e -> Objects.equals(id, e.getId())).map(BlogPageId::getDesc).findAny().orElse("");
    }

    public static Map<Long, String> getPageIdAndDescMap() {
        return Arrays.stream(values()).collect(Collectors.toMap(BlogPageId::getId, BlogPageId::getDesc));
    }

}
