package com.dimple.auth;

import com.dimple.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 认证授权中心
 *
 * @author Dimple
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DimpleAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleAuthApplication.class, args);
    }
}
