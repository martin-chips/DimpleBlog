package com.dimple.domain;

import com.dimple.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @className: Comment
 * @description:
 * @author: Dimple
 * @date: 06/19/20
 */
@Data
@Entity
@Table(name = "bg_comment")
public class Comment extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
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
