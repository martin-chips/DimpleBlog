package com.dimple.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : JpaUpdateUtil
 * @description :
 * @date : 01/02/19 14:03
 */

public class JpaUpdateUtil {

    /**
     * @param source 从数据库中查询出来的字段
     * @param target 需要提交的实体
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getIgnoreProperties(target));
    }

    private static String[] getIgnoreProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] propertyDescriptors = srcBean.getPropertyDescriptors();
        Set<String> emptyProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object propertyValue = srcBean.getPropertyValue(propertyDescriptor.getName());
            if (propertyValue != null) {
                emptyProperties.add(propertyDescriptor.getName());
            }
        }
        String[] result = new String[emptyProperties.size()];
        return emptyProperties.toArray(result);
    }

}
