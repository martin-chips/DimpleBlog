package com.dimple.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.crazycake.shiro.exception.SerializationException;
import org.crazycake.shiro.serializer.RedisSerializer;

import java.io.*;

/**
 * @ClassName: SerializeUtil
 * @Description: 序列化工具
 * @Auther: Dimple
 * @Date: 2019/1/12 13:05
 * @Version: 1.0
 */
@Slf4j
public class SerializeUtil implements RedisSerializer {


    public static boolean isEmpty(byte[] data) {
        return data == null || data.length == 0;
    }

    /**
     * 序列化
     *
     * @param object
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object object) throws SerializationException {
        byte result[] = null;
        if (object == null) {
            return new byte[0];
        }

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(SerializeUtil.class.getSimpleName() + "  requires a Serializable payload" + "but received an object of type [" + object.getClass().getName() + "]");
            }
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            result = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("fail to serialize,{}" + e);
        }
        return result;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        Object result = null;
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            result = objectInputStream.readObject();
        } catch (IOException e) {
            log.error("fail to deserialize,{}" + e);
        } catch (ClassNotFoundException e) {
            log.error("fail to deserialize,{}" + e);
        }
        return result;
    }
}
