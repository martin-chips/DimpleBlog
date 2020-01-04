package com.dimple.project.system.domain;

import com.dimple.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className: DictType
 * @description: 字典类型表 sys_dict_type
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
//序列化Json的时候,如果是Null则忽略
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class DictType extends BaseEntity implements Serializable {

    /**
     * 字典主键
     */
    private Long id;

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
