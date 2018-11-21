package com.dimple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebAppConfigurerCustom
 * @Description:
 * @Auther: Owenb
 * @Date: 11/21/18 10:21
 * @Version: 1.0
 */
@Configuration
public class WebAppConfigurerCustom implements WebMvcConfigurer {
    /**
     * 配置上传文件资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:D:/tempfile/");
    }
}
