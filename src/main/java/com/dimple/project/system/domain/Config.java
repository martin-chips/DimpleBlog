package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: Config
 * @description: 参数配置表 sys_config
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
@NoArgsConstructor
public class Config extends BaseEntity {
    /**
     * 参数主键
     */
    private Long id;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;
}
