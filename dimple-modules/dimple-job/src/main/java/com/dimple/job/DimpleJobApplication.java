package com.dimple.job;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableRyFeignClients;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 定时任务
 *
 * @author Dimple
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class DimpleJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleJobApplication.class, args);
    }
}
