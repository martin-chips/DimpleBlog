package com.dimple.project.book.entity;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: NoteTag
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
@Data
@NoArgsConstructor
public class NoteTag extends BaseEntity {
    private Long id;
    /**
     * 标签名
     */
    private String title;
    /**
     * 标签颜色
     */
    private String color;

    /**
     * 关联的Note的数量
     */
    private Long count;

    public NoteTag(String title, String color) {
        this.title = title;
        this.color = color;
    }
}
