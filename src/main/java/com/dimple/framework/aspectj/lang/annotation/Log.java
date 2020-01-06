package com.dimple.framework.aspectj.lang.annotation;

import com.dimple.framework.aspectj.lang.enums.BusinessType;
import com.dimple.framework.aspectj.lang.enums.OperatorType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: Log
 * @description: an annotation for record the log
 * @author: Dimple
 * @date: 10/22/19
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * the module of this log
     */
    String title() default "";

    /**
     * business type
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * the operator type
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * is save the request data?
     * if the data is password or other privacy
     */
    boolean isSaveRequestData() default true;
}
