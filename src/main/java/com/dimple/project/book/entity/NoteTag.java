package com.dimple.project.book.entity;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: NoteTag
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
@Data
public class NoteTag extends BaseEntity {
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
     * 关联的Note的数量
     */
    private Long count;
}
