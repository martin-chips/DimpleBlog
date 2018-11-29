package com.dimple.config;

import com.dimple.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.annotation.Resources;

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

    @Autowired
    RequestInterceptor requestInterceptor;

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
    }
}
