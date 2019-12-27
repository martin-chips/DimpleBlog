package com.dimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: DimpleBlogApplication
 * @description: 启动程序
 * @author: Dimple
 * @date: 10/22/19
 */
@SpringBootApplication
public class DimpleBlogApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(DimpleBlogApplication.class, args);
    }
}
