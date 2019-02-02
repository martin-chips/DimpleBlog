package com.dimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
//@EnableCaching
//@EnableAsync
//@EnableJpaRepositories
//@EnableSwagger2
public class DimpleBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DimpleBlogApplication.class, args);
    }
}
