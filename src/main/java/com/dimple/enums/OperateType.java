package com.dimple.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: OperateType
 * @Description: 操作的类型
 * @Auther: Owenb
 * @Date: 12/05/18 14:12
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum OperateType {
    /**
     * 其它
     */
    OTHER(0, "其他"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 更新
     */
    UPDATE(2, "更新"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 清空
     */
    CLEAN(4, "清空");

    private Integer type;
    private String name;
}


