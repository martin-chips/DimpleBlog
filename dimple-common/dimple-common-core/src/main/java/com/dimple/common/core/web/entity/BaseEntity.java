package com.dimple.common.core.web.entity;

import lombok.Data;

import java.util.Date;

/**
 * BaseEntity
 *
 * @author Dimple
 * @date 12/26/2022 9:12 AM
 */
@Data
public class BaseEntity {

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;
}
