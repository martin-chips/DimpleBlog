package com.dimple.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SpringUtil
 * @Description: Spring的工具类，方便在非Spring环境中获取Bean
 * @Auther: Owenb
 * @Date: 11/29/18 18:09
 * @Version: 1.0
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    /**
     * 根据Bean的Name获取实例
     *
     * @param name Bean的name
     * @param <T>  泛型
     * @return 一个Bean实例
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据类型获取Bean
     *
     * @param clz 类型
     * @param <T> 泛型
     * @return 根据类型找到的bean的实例
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        T result = (T) applicationContext.getBean(clz);
        return result;
    }

    /**
     * 是否包含要查找的bean
     *
     * @param name bean的name
     * @return
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /***
     * 判断所给的bean是不是单例
     * @param name bean的名称
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取对象的类型
     *
     * @param name 要确定的bean的名称
     * @return 对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }
}
