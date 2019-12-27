package com.dimple.common.enums;

/**
 * @className: CategoryType
 * @description: 分类的类型
 * @author: Dimple
 * @date: 12/25/19
 */
public enum CategoryType {
    Blog(1), Note(2), Book(3);
    private Integer type;

    CategoryType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
