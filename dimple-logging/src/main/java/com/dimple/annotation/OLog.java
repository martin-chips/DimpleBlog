package com.dimple.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @className: OperateLog
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OLog {
    String value() default "";
}
