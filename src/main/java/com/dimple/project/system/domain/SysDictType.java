package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * @className: SysDictType
 * @description: 字典类型表 sys_dict_type
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
public class SysDictType extends BaseEntity {

    /**
     * 字典主键
     */
    private Long dictId;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

}
