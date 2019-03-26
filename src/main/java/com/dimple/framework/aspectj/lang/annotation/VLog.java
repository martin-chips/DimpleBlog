package com.dimple.framework.aspectj.lang.annotation;

import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.aspectj.lang.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @className: VLog
 * @description: 自定义访问日志注解
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {
    /**
     * 请求的模块
     */
    String title() default "";

    int blogId() default 0;
}
