package com.dimple.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * @ClassName: Md5Util
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 20:05
 * @Version: 1.0
 */
@Slf4j
public class Md5Util {
    /**
     * 生成加密后的密码
     *
     * @param password 原始密 码
     * @param salt     盐
     * @return
     */
    public static String generatorMd5(String password, String salt) {
        String hashAlgorithmName = "md5";
        ByteSource saltByteSource = ByteSource.Util.bytes(salt);
        int hashIterations = 2;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, password, saltByteSource, hashIterations);
        String s = simpleHash.toString();
        return s;
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            log.error("not supported charset...{}", e);
            return s;
        }
    }

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    public static void main(String[] args) {
//        在此处修改需要生成密文的明文密码和盐
        String uuid = generatorMd5("bxf960805", "dimple");
        System.out.println(uuid);
    }
}
