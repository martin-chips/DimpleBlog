package com.dimple.common.enums;

/**
 * @className: TagType
 * @description: 标签的类型
 * @author: Dimple
 * @date: 12/25/19
 */
public enum TagType {
    Blog(1), Note(2), Book(3);
    private Integer type;

    TagType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
