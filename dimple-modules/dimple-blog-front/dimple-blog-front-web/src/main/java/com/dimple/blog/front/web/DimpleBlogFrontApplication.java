package com.dimple.blog.front.web;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableDimpleFeignClients;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Blog前台
 *
 * @author Dimple
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableDimpleFeignClients
@SpringBootApplication(scanBasePackages = {"com.dimple.blog.front"})
public class DimpleBlogFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleBlogFrontApplication.class, args);
    }
}
