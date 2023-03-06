package com.dimple.system.api.model;

import lombok.Data;

/**
 * VisitLogBO
 *
 * @author Dimple
 * @date 3/6/2023 7:38 PM
 */
@Data
public class BlogVisitLogBO {
    private Long id;
    private String title;
    private Long pageId;
    private String requestUri;
    private String referer;
    private String userAgent;
    private Integer statusCode;
    private String controllerName;
    private String methodName;
    private String requestMethod;
    private String requestParams;
    private String responseParams;
    private String os;
    private String spider;
    private String browser;
    private String ip;
    private String location;
    private String exception;
}
