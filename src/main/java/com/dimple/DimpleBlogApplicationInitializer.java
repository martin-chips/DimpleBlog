package com.dimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//@EnableCaching
//@EnableAsync
//@EnableJpaRepositories
//@EnableSwagger2
public class DimpleBlogApplicationInitializer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DimpleBlogApplicationInitializer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
