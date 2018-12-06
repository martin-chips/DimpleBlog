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
    OTHER(0),

    /**
     * 新增
     */
    INSERT(1),

    /**
     * 更新
     */
    UPDATE(2),

    /**
     * 删除
     */
    DELETE(3),

    /**
     * 清空
     */
    CLEAN(4);


    private Integer type;
}


