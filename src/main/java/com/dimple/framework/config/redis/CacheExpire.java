package com.dimple.framework.config.redis;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.dimple.framework.config.redis.TimeType.SECONDS;

/**
 * @className: CacheExpire
 * @description:
 * @author: Dimple
 * @date: 2020/1/1
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheExpire {

    /**
     * expire time, default 60s
     */
    @AliasFor("expire")
    long value() default 60L;

    /**
     * expire time, default 60s
     */
    @AliasFor("value")
    long expire() default 60L;

    /**
     * 时间单位
     *
     * @return
     */
    TimeType type() default SECONDS;

}
