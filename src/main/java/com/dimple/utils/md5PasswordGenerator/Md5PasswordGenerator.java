package com.dimple.utils.md5PasswordGenerator;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: Md5PasswordGenerator
 * @Description:
 * @Auther: Owenb
 * @Date: 11/19/18 20:05
 * @Version: 1.0
 */
public class Md5PasswordGenerator {
    public static String generatorMd5(String password, String salt) {
        String hashAlgorithmName = "md5";
        ByteSource saltByteSource = ByteSource.Util.bytes(salt);
        int hashIterations = 2;
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, password, saltByteSource, hashIterations);
        String s = simpleHash.toString();
        return s;
    }

    public static void main(String[] args) {
//        在此处修改需要生成密文的明文密码和盐
        String uuid = generatorMd5("123456", "uuid");
        System.out.println(uuid);
    }
}
