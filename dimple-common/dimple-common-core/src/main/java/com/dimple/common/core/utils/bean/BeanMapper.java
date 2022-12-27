package com.dimple.common.core.utils.bean;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * NewBeanUtils
 *
 * @author Dimple
 * @date 12/26/2022 8:41 AM
 */
public class BeanMapper {
    private static final MapperFactory FACTORY = new DefaultMapperFactory.Builder().build();

    /**
     * 缓存实例集合
     */
    private static final Map<String, MapperFacade> CACHE_MAPPER = new ConcurrentHashMap<>();

    private final MapperFacade mapper;

    public BeanMapper(MapperFacade mapper) {
        this.mapper = mapper;
    }

    /**
     * 转换实体函数
     *
     * @param sourceEntity 源实体
     * @param targetClass  目标类对象
     * @param refMap       配置源类与目标类不同字段名映射
     * @param <S>          源泛型
     * @param <T>          目标泛型
     * @return 目标实体
     */
    public static <S, T> T convert(S sourceEntity, Class<T> targetClass, Map<String, String> refMap) {
        if (sourceEntity == null) {
            return null;
        }
        return classMap(sourceEntity.getClass(), targetClass, refMap).map(sourceEntity, targetClass);
    }

    /**
     * 转换实体函数
     *
     * @param sourceEntity 源实体
     * @param targetClass  目标类对象
     * @param <S>          源泛型
     * @param <T>          目标泛型
     * @return 目标实体
     */
    public static <S, T> T convert(S sourceEntity, Class<T> targetClass) {
        return convert(sourceEntity, targetClass, null);
    }

    /**
     * 转换实体集合函数
     *
     * @param sourceEntityList 源实体集合
     * @param targetClass      目标类对象
     * @param refMap           配置源类与目标类不同字段名映射
     * @param <S>              源泛型
     * @param <T>              目标泛型
     * @return 目标实体集合
     */
    public static <S, T> List<T> convertList(List<S> sourceEntityList, Class<T> targetClass, Map<String, String> refMap) {
        if (sourceEntityList == null) {
            return null;
        }
        if (sourceEntityList.size() == 0) {
            return new ArrayList<>(0);
        }
        return classMap(sourceEntityList.get(0).getClass(), targetClass, refMap).mapAsList(sourceEntityList, targetClass);
    }

    /**
     * 转换实体集合函数
     *
     * @param sourceEntityList 源实体集合
     * @param targetClass      目标类对象
     * @param <S>              源泛型
     * @param <T>              目标泛型
     * @return 目标实体集合
     */
    public static <S, T> List<T> convertList(List<S> sourceEntityList, Class<T> targetClass) {
        return convertList(sourceEntityList, targetClass, null);
    }


    /**
     * 注册属性
     *
     * @param source 源类
     * @param target 目标类
     * @param refMap 属性转换
     */
    public static <V, P> void register(Class<V> source, Class<P> target, Map<String, String> refMap) {
        if (CollectionUtils.isEmpty(refMap)) {
            FACTORY.classMap(source, target).byDefault().register();
        } else {
            ClassMapBuilder<V, P> classMapBuilder = FACTORY.classMap(source, target);
            refMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
        }
    }

    /**
     * 属性名称一致可用
     *
     * @param source 源数据
     * @param target 目标对象
     * @return OrikaUtils
     */
    private static <V, P> BeanMapper classMap(Class<V> source, Class<P> target) {
        return classMap(source, target, null);
    }

    /**
     * 属性名称不一致可用
     *
     * @param source 原对象
     * @param target 目标对象
     * @return OrikaUtils
     */
    private static synchronized <V, P> BeanMapper classMap(Class<V> source, Class<P> target, Map<String, String> refMap) {
        String key = source.getCanonicalName() + ":" + target.getCanonicalName();
        if (CACHE_MAPPER.containsKey(key)) {
            return new BeanMapper(CACHE_MAPPER.get(key));
        }
        register(source, target, refMap);
        MapperFacade mapperFacade = FACTORY.getMapperFacade();
        CACHE_MAPPER.put(key, mapperFacade);

        return new BeanMapper(mapperFacade);
    }


    /**
     * Orika复制对象
     *
     * @param source 源数据
     * @param target 目标对象
     * @return target
     */
    private <V, P> P map(V source, Class<P> target) {
        return mapper.map(source, target);
    }

    /**
     * 复制List
     *
     * @param source 源对象
     * @param target 目标对象
     * @return P
     */
    private <V, P> List<P> mapAsList(List<V> source, Class<P> target) {
        return CollectionUtils.isEmpty(source) ? Collections.emptyList() : mapper.mapAsList(source, target);
    }
}