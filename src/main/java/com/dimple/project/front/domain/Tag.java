package com.dimple.project.front.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Tag
 * @description:
 * @author: Dimple
 * @date: 11/19/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private String title;
    private Long count;
}
