package com.dimple.log.web.controller.vo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * VisitLogVO
 *
 * @author Dimple
 * @date 3/6/2023 8:30 PM
 */
@Data
public class BlogVisitLogVO extends BaseEntity {
    private Long id;
    private String title;
    private Long pageId;
    private String requestUri;
    private String referer;
    private Integer statusCode;
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
