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
 * @description : 解决JPA更新的时候也会将为null的字段同步更新的问题
 * @date : 12/28/18 19:31
 */
public class JpaUpdateUtil {
    /**
     * 我们可以将目标源中需要改变的属性值过滤掉以后，将数据源中的数据复制到目标源中，这样就达到了，只是更新需要改变的属性值，不需要更新的保持不变
     *
     * @param target
     * @param source
     */
    public static void copyNullProperties(Object target, Object source) {
        BeanUtils.copyProperties(source, target, getNotNullProperties(target));
    }

    private static String[] getNotNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        PropertyDescriptor[] propertyDescriptors = srcBean.getPropertyDescriptors();
        Set<String> notNullName = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object value = srcBean.getPropertyValue(propertyDescriptor.getName());
            if (value != null) {
                notNullName.add(propertyDescriptor.getName());
            }
        }
        String[] result = new String[notNullName.size()];
        return notNullName.toArray(result);
    }
}
