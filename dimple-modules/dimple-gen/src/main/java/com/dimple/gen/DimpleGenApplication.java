package com.dimple.gen;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.security.annotation.EnableDimpleFeignClients;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成
 *
 * @author Dimple
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableDimpleFeignClients
@SpringBootApplication
public class DimpleGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleGenApplication.class, args);
    }
}
