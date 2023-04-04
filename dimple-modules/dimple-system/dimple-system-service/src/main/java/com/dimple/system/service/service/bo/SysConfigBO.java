package com.dimple.system.service.service.bo;

import com.dimple.common.core.web.entity.BaseEntity;
import lombok.Data;

/**
 * SysConfigBO
 *
 * @author Dimple
 */
@Data
public class SysConfigBO extends BaseEntity {
    /**
     * 参数主键
     */
    private Long id;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 系统内置（Y是 N否）
     */
    private String configType;
}
