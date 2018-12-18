package com.dimple.framework.config;

import com.dimple.framework.interceptor.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
@Slf4j
public class WebAppConfigurerCustom implements WebMvcConfigurer {

    @Value("${dimple.ImgUploadDir.windows}")
    String imgDirWindows;


    @Value("${dimple.ImgUploadDir.linux}")
    String imgDirLinux;


    /**
     * 配置上传文件资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("windows")) {
            log.info("当前图片上传的路径为： " + "file:" + imgDirWindows);
            registry.addResourceHandler("/images/**").addResourceLocations("file:" + imgDirWindows);
        } else {
            log.info("当前图片上传的路径为： " + imgDirLinux);
            registry.addResourceHandler("/images/**").addResourceLocations(imgDirLinux);
        }
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
