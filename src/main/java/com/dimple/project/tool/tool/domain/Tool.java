package com.dimple.project.tool.tool.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: 
 * @description: 常用工具(Tool)实体类
 * @auther: Dimple
 * @date: 09/04/2019
 * @version: 1.1
 */
@Data
public class Tool extends BaseEntity {
    private static final long serialVersionUID = -29134451060966794L;
    
    private Integer toolId;
    //工具名称
    private String toolName;
    //工具描述	
    private String description;
    //显示状态，1表示显示，0表示不显示
    private String display;
    //链接地址
    private String url;
    
    private String type;
    //头像地址
    private String headImg;
    //权重
    private Integer weight;
    
    private Integer toolCategoryId;
}