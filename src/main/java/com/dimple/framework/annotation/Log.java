package com.dimple.framework.annotation;

import com.dimple.constant.OperatorType;

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
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    String action() default "";

    /**
     * 渠道
     */
    String channel() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
