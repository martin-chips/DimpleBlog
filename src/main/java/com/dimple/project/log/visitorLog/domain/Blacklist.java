package com.dimple.project.log.visitorLog.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @className: Blacklist
 * @description: 黑名单
 * @auther: Dimple
 * @date: 03/26/19
 * @version: 1.0
 */
@Data
public class Blacklist extends BaseEntity {
    private Integer id;
    private String ipAddr;
    private String description;
    private Integer count;
    private Date lastAccessTime;
    private String lastAccessUrl;
}
