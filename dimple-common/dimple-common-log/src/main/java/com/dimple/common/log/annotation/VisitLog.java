package com.dimple.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * VisitLog
 *
 * @author Dimple
 * @date 3/6/2023 7:40 PM
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VisitLog {
    /**
     * 模块
     */
    String title() default "";

    /**
     * the page id mark the page that visitor accessed.
     */
    String pageId() default "";

    boolean saveRequestData() default true;

    boolean saveResponseData() default false;
}
