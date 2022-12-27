package com.dimple.blog.web;

import com.dimple.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomSwagger2
@SpringBootApplication
public class DimpleBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleBlogApplication.class, args);
    }
}
