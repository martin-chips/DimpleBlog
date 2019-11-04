package com.dimple.project.monitor.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @className: Blacklist
 * @description:
 * @author: Dimple
 * @date: 10/24/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
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
