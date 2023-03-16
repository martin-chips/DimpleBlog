package com.dimple.log.web.controller.vo.params;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * VisitLogVOParams
 *
 * @author Dimple
 * @date 3/6/2023 8:31 PM
 */
@Data
public class BlogVisitLogVOParams extends BaseEntity {
    private String title;
    private Long pageId;
    private String requestUri;
    private String referer;
    private String userAgent;
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
