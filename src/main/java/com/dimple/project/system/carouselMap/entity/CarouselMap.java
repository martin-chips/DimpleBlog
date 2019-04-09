package com.dimple.project.system.carouselMap.entity;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: BlogController
 * @description: 轮播图设置(CarouselMap)实体类
 * @auther: Dimple
 * @Date: 2019/4/8
 * @Version: 1.1
 */
@Data
public class CarouselMap extends BaseEntity {
    private static final long serialVersionUID = -42774646867334310L;

    private Integer carouselId;
    //背景图片地址
    private String imgUrl;
    //标题
    private String title;
    //副标题
    private String subTitle;
    //点击的url地址
    private String url;
    //是否当前窗口打开，0表示否，1表示是
    private String target;
    //是否显示
    private String display;
}