package com.dimple.modules.FrontDeskModule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: CategoryDomain
 * @description:
 * @auther: Owenb
 * @date: 01/21/19 20:26
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class CategoryDomain {
    private int categoryId;
    private String title;
    private String description;
}
