package com.dimple.project.common.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: Tag
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
@Data
@NoArgsConstructor
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag extends BaseEntity implements Serializable {
    private Long id;
    /**
     * 标签颜色
     */
    private String color;
    /**
     * 标签名
     */
    private String title;
    /**
     * 标签类型,1表示博客的Tag,2表示笔记的Tag
     */
    private Integer type;
    /**
     * 关联数量
     */
    private Long count;

    public Tag(String title, String color, Integer type) {
        this.color = color;
        this.title = title;
        this.type = type;
    }
}
