package com.dimple.auth;

import com.dimple.common.security.annotation.EnableDimpleFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 认证授权中心
 *
 * @author Dimple
 */
@EnableDimpleFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.dimple.auth"})
public class DimpleAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleAuthApplication.class, args);
    }
}
