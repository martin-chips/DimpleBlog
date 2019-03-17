package com.dimple;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @className: DimpleBlogServletInitializer
 * @description: web容器中进行部署
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
public class DimpleBlogServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DimpleBlogApplication.class);
    }
}
