package com.dimple.common.enums;

/**
 * @className: TagType
 * @description: the type of tag
 * @author: Dimple
 * @date: 12/25/19
 */
public enum TagType {
    /**
     * tag of blog
     */
    BLOG(1),
    /**
     * tag of note
     */
    NOTE(2),
    /**
     * tag of book
     */
    BOOK(3);

    private Integer type;

    TagType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
