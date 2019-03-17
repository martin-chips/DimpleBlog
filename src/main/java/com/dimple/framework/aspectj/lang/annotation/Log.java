package com.dimple.framework.aspectj.lang.annotation;

import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.aspectj.lang.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @className: Log
 * @description: 自定义操作日志记录注解
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
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
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
