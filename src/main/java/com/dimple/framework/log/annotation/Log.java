package com.dimple.framework.log.annotation;

import com.dimple.framework.enums.OperateType;

import java.lang.annotation.*;

/**
 * @ClassName: Log
 * @Description: 操作日志记录注解
 * @Auther: Owenb
 * @Date: 11/29/18 10:30
 * @Version: 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 操作的模块（比如友链管理模块）
     */
    String title() default "";

    /**
     * 操作的类型(比如新增、删除)
     */
    OperateType operateType() default OperateType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
