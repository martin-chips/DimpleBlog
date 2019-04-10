package com.dimple.project.tool.toolCategory.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: ToolCategory
 * @description: 工具的分类(ToolCategory)实体类
 * @auther: Dimple
 * @date: 09/04/2019
 * @version: 1.1
 */
@Data
public class ToolCategory extends BaseEntity {
    private static final long serialVersionUID = -63065711007716650L;

    private Integer toolCategoryId;
    //分类的标题
    private String toolCategoryTitle;
    //权重
    private Integer weight;
    //是否显示，1表示显示，0表示不显示
    private String display;
}