package com.dimple.common.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @className: ObjectUtils
 * @description:
 * @author: Dimple
 * @date: 11/22/19
 */
public class ObjectUtils {
    private ObjectUtils() {
    }

    /**
     * 检验Object是否为Null或者Empty,支持List,Map
     *
     * @param object ob
     * @return 是否为empty
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        boolean isEmpty = false;
        if (object instanceof CharSequence) {
            if (object instanceof String) {
                isEmpty = ((String) object).trim().length() == 0;
            } else {
                isEmpty = ((CharSequence) object).length() == 0;
            }
        } else if (object instanceof Map) {
            isEmpty = ((Map) object).isEmpty();
        } else if (object instanceof Iterable) {
            if (object instanceof Collection) {
                isEmpty = ((Collection) object).isEmpty();
            } else {
                isEmpty = !((Iterable) object).iterator().hasNext();
            }
        } else if (object.getClass().isArray()) {
            isEmpty = Array.getLength(object) == 0;
        }
        return isEmpty;
    }

    /**
     * 是否不为空
     *
     * @param object object
     * @return list
     */
    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
