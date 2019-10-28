package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: SysLink
 * @description: 友链表 sys_link
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
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
    private Object status;
    /**
     * 是否显示友链
     */
    private Object display;
    /**
     * 站长邮箱地址
     */
    private String email;
    /**
     * 权重
     */
    private Long weight;
}