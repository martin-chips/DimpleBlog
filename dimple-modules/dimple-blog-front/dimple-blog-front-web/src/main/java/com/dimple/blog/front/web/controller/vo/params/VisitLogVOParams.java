package com.dimple.blog.front.web.controller.vo.params;

import lombok.Data;

/**
 * VisitLogVOParams
 *
 * @author Dimple
 */
@Data
public class VisitLogVOParams {
    private String title;
    private Long pageId;
    private String requestUri;
    private String referer;
    private Integer statusCode;
    private String userAgent;
    private String os;
    private String ip;
}
