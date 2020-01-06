package com.dimple.common.enums;

/**
 * @className: CategoryType
 * @description: the type of category
 * @author: Dimple
 * @date: 12/25/19
 */
public enum CategoryType {
    /**
     * type of blog
     */
    BLOG(1),
    /**
     * type of note
     */
    NOTE(2),
    /**
     * type of book
     */
    BOOK(3);
    private Integer type;

    CategoryType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
