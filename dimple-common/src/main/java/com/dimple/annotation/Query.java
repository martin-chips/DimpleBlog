package com.dimple.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Query
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    // 基本对象的属性名
    String propName() default "";

    //查询方式
    Type type() default Type.EQUAL;

    /**
     * 连接查询的属性名
     */
    String joinName() default "";

    /**
     * 默认左连接
     */
    Join join() default Join.LEFT;

    /**
     * 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开, 如@Query(blurry = "email,username")
     */
    String blurry() default "";

    enum Type {
        EQUAL, GREATER_THAN, LESS_THAN, INNER_LIKE, LEFT_LIKE, RIGHT_LIKE, LESS_THAN_NQ, IN, NOT_EQUAL, BETWEEN, NOT_NULL, IS_NULL
    }

    /**
     * 适用于简单连接查询，复杂的请自定义该注解，或者使用sql查询
     */
    enum Join {
        LEFT, RIGHT
    }

}

