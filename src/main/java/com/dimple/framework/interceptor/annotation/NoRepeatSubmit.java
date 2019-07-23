package com.dimple.framework.interceptor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: NoRepeatSubmit
 * @description: 禁止表单重复提交注解
 * @auther: Dimple
 * @date: 07/23/19
 * @version: 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface NoRepeatSubmit {
    /**
     * 默认间隔时间
     *
     * @return 默认间隔时间（单位：s）
     */
    int intervalSecond() default 5;
}
