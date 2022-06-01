package com.dimple.system;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableCustomerFeignClients;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 系统模块
 *
 * @author Dimple
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCustomerFeignClients
@SpringBootApplication
public class DimpleSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleSystemApplication.class, args);
    }
}
