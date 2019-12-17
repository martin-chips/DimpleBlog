package com.dimple.project.book.entity;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: BookCategory
 * @description:
 * @author: Dimple
 * @date: 12/16/19
 */
@Data
public class BookCategory extends BaseEntity {
    private Long id;
    private String title;
    private String description;
}
