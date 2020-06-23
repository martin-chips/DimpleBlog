package com.dimple.annotation;

/**
 * @className: VisitLog
 * @description: visit log annotation
 * @author: Dimple
 * @date: 06/17/20
 */
public @interface VLog {
    /**
     * the module contains Homepage , Article page ,About page and so on.
     */
    String title() default "";

    /**
     * page Id
     */
    String pageId() default "";
}
