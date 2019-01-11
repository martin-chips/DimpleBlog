package com.dimple.framework.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @className: VLog
 * @description:
 * @auther: Dimple
 * @date: 01/10/19 20:12
 * @version: 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {

    //请求的模块：网站首页，技术博客，资源分享，随心吐槽，关于我，留言，登录
    String title() default "";

    //请求的博文的Id
    int blogId() default 0;
}
