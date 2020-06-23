package com.dimple.utils;

import org.junit.Test;

import static com.dimple.utils.EncryptUtils.desDecrypt;
import static com.dimple.utils.EncryptUtils.desEncrypt;
import static org.junit.Assert.assertEquals;

/**
 * @className: EncryptUtilsTest
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
public class EncryptUtilsTest {

    /**
     * 对称加密
     */
    @Test
    public void testDesEncrypt() {
        try {
            assertEquals("7772841DC6099402", desEncrypt("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对称解密
     */
    @Test
    public void testDesDecrypt() {
        try {
            assertEquals("123456", desDecrypt("7772841DC6099402"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
