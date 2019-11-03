package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: Carousel
 * @description:
 * @author: Dimple
 * @date: 11/02/19
 */
@Data
public class Carousel extends BaseEntity {
    private static final long serialVersionUID = 625546578481872329L;

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