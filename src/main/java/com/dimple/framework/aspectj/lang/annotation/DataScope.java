package com.dimple.framework.aspectj.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: DataScope
 * @description: 数据权限过滤注解
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 表的别名
     */
     String tableAlias() default "";
}
