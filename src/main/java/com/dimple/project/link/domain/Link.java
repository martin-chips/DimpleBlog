package com.dimple.project.link.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: Link
 * @description: 友链实体类
 * @auther: Dimple
 * @Date: 2019/3/17
 * @Version: 1.0
 */
@Data
public class Link extends BaseEntity {

    private Integer linkId;
    private String title;
    private String description;
    private String headerImg;
    private Integer click;
    private Integer weight;
    private Integer available;
    private Integer processed;
    private Integer display;
    private String email;
    private String url;

}
