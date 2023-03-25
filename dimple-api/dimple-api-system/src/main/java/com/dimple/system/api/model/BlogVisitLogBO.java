package com.dimple.system.api.model;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * VisitLogBO
 *
 * @author Dimple
 * @date 3/6/2023 7:38 PM
 */
@Data
public class BlogVisitLogBO extends BaseEntity {
    private Long id;
    private String title;
    private Long pageId;
    private String requestUri;
    private String referer;
    private Integer statusCode;
    private String methodName;
    private String requestMethod;
    private String userAgent;
    private String requestParams;
    private String responseParams;
    private String os;
    private String spider;
    private String browser;
    private String ip;
    private String location;
    private String exception;
}
