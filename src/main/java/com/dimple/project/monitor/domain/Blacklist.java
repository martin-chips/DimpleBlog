package com.dimple.project.monitor.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @className: Blacklist
 * @description:
 * @author: Dimpleb
 * @date: 10/24/19
 */
@Data
public class Blacklist extends BaseEntity {
    /**
     * id
     */
    private Long id;
    /**
     * ip 地址
     */
    private String ip;
    /**
     * 封禁原因描述
     */
    private String description;
    /**
     * 拦截次数
     */
    private Long interceptCount;
    /**
     * 上次拦截的Url
     */
    private String lastAccessUrl;
    /**
     * 上次访问的地址
     */
    private Date lastAccessTime;
}
