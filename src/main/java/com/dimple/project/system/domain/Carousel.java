package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Carousel
 * @description:
 * @author: Dimple
 * @date: 11/02/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Carousel extends BaseEntity {

    private Long id;
    /**
     * 显示文本
     */
    private String description;
    /**
     * 点击次数
     */
    private Long click;
    /**
     * 图片URL
     */
    private String imgUrl;
    /**
     * 是否显示
     */
    private Boolean display;
    /**
     * 是否当前窗口打开
     */
    private Boolean target;

}