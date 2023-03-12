package com.dimple.file.web;

import com.dimple.common.security.annotation.EnableCustomConfig;
import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文件服务
 *
 * @author Dimple
 */
@EnableCustomSwagger2
@EnableCustomConfig
@SpringBootApplication( scanBasePackages = {"com.dimple.file"})
public class DimpleFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleFileApplication.class, args);
    }
}
