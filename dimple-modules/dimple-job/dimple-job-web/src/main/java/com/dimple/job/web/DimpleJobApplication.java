package com.dimple.job.web;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableDimpleFeignClients;
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
@EnableDimpleFeignClients
@SpringBootApplication
public class DimpleJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleJobApplication.class, args);
    }
}
