package com.dimple.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @className: RsaProperties
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@Data
@Component
public class RsaProperties {

    public static String privateKey;

    @Value("${rsa.private_key}")
    public void setPrivateKey(String privateKey) {
        RsaProperties.privateKey = privateKey;
    }
}
