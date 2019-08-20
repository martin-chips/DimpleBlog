package com.dimple.project.log.visitorLog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: VLog
 * @description:
 * @auther: Dimple
 * @Date: 2019/3/16
 * @Version: 1.0
 */
@Data
public class VisitLog extends BaseEntity {
    private Integer visitId;
    /**
     * IP地址
     */
    private String ipAddr;
    /**
     * ip地理位置
     */
    private String location;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统类型
     */
    private String os;
    /**
     * 爬虫
     */
    private String spider;
    /**
     * 请求的地址
     */
    private String requestUrl;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 访问的模块
     */
    private String title;
    /**
     * 访问状态
     */
    private Integer status;
    /**
     * 入口地址
     */
    private String entryUrl;
}
