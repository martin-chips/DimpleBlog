package com.dimple.system.api.model;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 *
 * @author Dimple
 */
@Data
public class SysLogininforBO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 状态 0成功 1失败
     */
    private String status;

    /**
     * 地址
     */
    private String ip;

    /**
     * 描述
     */
    private String msg;
}