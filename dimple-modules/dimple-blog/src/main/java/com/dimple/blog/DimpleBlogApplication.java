package com.dimple.blog;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableCustomerFeignClients;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DimpleBlogApplication
 *
 * @author BianXiaofeng
 * @date 2022/6/2 10:11
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableCustomerFeignClients
@SpringBootApplication
public class DimpleBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleBlogApplication.class, args);
    }
}
