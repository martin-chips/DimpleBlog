package com.dimple.exception;

import org.springframework.util.StringUtils;

/**
 * @className: EntityNotFoundException
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " does not exist";
    }
}
