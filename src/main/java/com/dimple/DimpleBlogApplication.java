package com.dimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DimpleBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DimpleBlogApplication.class, args);
    }
}
