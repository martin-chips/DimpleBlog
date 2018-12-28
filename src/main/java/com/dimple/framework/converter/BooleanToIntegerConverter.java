package com.dimple.framework.converter;

import javax.persistence.AttributeConverter;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : BooleanToIntegerConverter
 * @description :
 * @date : 12/27/18 11:09
 */
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Byte> {

    @Override
    public Byte convertToDatabaseColumn(Boolean support) {
        if (support == null) {
            throw new RuntimeException("参数转换失败！");
        }
        if (support == false) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public Boolean convertToEntityAttribute(Byte support) {
        if (support == null) {
            throw new RuntimeException("参数转换失败！");
        }
        if (support == 1) {
            return true;
        } else {
            return false;
        }
    }
}
