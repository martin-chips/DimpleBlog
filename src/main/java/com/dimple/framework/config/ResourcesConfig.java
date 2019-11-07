package com.dimple.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

/**
 * @className: ResourcesConfig
 * @description: 通用配置
 * @author: Dimple
 * @date: 10/22/19
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置上传的文件的访问路径映射
        String pathUtl = Paths.get(DimpleBlogConfig.getProfile()).normalize().toUri().toASCIIString();
        registry.addResourceHandler("/file/**").addResourceLocations(pathUtl).setCachePeriod(0);

        /** swagger配置 */
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
