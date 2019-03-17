package com.dimple.framework.aspectj.lang.annotation;

import com.dimple.framework.aspectj.lang.enums.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: DataSource
 * @description: 自定义多数据源切换注解
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    /**
     * 切换数据源名称
     */
     DataSourceType value() default DataSourceType.MASTER;
}
