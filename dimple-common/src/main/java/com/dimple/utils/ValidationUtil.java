package com.dimple.utils;

import cn.hutool.core.util.ObjectUtil;
import com.dimple.exception.BadRequestException;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

/**
 * @className: ValidationUtil
 * @description: 验证工具
 * @author: Dimple
 * @date: 06/17/20
 */
public class ValidationUtil {

    /**
     * 验证空
     */
    public static void isNull(Object obj, String entity, String parameter, Object value) {
        if (ObjectUtil.isNull(obj)) {
            String msg = entity + " 不存在: " + parameter + " is " + value;
            throw new BadRequestException(msg);
        }
    }

    /**
     * 验证是否为邮箱
     */
    public static boolean isEmail(String email) {
        return new EmailValidator().isValid(email, null);
    }
}
