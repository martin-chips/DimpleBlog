package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @className: Link
 * @description: 友链对象 sys_link
 * @author: Dimple
 * @date: 2019-10-29
 */
@Data
@NoArgsConstructor
public class Link extends BaseEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 友链名称
     */
    private String title;

    /**
     * 友链地址
     */
    private String url;

    /**
     * 友链描述
     */
    private String description;

    /**
     * 网站图片
     */
    private String headerImg;

    /**
     * 1表示审核通过,0表示未审核
     */
    private Boolean status;

    /**
     * 是否显示友链
     */
    private Boolean display;

    /**
     * 站长邮箱地址
     */
    private String email;

    /**
     * 权重
     */
    private Long weight;

    /**
     * $column.columnComment
     */
    private String deleteBy;

    /**
     * $column.columnComment
     */
    private Date deleteTime;

}
