package com.dimple.service.Dto;

import lombok.Data;

/**
 * @className: CommentDTO
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Data
public class CommentDTO {
    private Long id;

    private String nickName;

    private String email;

    private String requestIp;

    private String address;

    private String os;

    private String browser;

    private Long parentId;

    private String qqNum;

    private String avatar;

    private Long pageId;

    private String requestUrl;

    private Boolean display;

    private Long good;

    private Long bad;

    private String content;

    private String htmlContent;

    private Boolean reply;

    private Boolean adminReply;

}
