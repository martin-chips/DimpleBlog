package com.dimple.framework.aspectj.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: VLog
 * @description: 前台访问日志记录
 * @author: Dimple
 * @date: 2019/11/30
 */
@Target({ElementType.PACKAGE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {
    /**
     * 请求的模块
     */
    String title() default "";

    /**
     * page Id
     */
    String pageId() default "";
}
