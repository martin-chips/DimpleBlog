package com.dimple.project.log.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: VisitLog
 * @description:
 * @author: Dimple
 * @date: 2019/11/30
 */
@Data
public class VisitLog extends BaseEntity implements Serializable {

    private Long id;
    /**
     * IP 地址
     */
    private String ip;
    /**
     * 地理位置
     */
    private String location;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 爬虫
     */
    private String spider;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求模块
     */
    private String title;
    /**
     * 请求状态
     */
    private Boolean status;
    /**
     * 请求失败信息
     */
    private String errorMsg;
    /**
     * 入口地址
     */
    private String entryUrl;
    /**
     * 页面id
     */
    private Long pageId;
}
