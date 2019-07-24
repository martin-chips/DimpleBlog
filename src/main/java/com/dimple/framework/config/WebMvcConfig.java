package com.dimple.framework.config;

import com.dimple.framework.interceptor.impl.RequestInterceptor;
import com.dimple.framework.interceptor.impl.NoRepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @className: WebMvcConfig
 * @description: 资源文件配置加载
 * @auther: Dimple
 * @Date: 2019/3/13
 * @Version: 1.1
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    RequestInterceptor requestInterceptor;
    @Autowired
    NoRepeatSubmitInterceptor noRepeatSubmitInterceptor;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        //添加黑名单拦截器
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**").excludePathPatterns("/error/blacklist");
        registry.addInterceptor(noRepeatSubmitInterceptor).addPathPatterns("/**");
    }
}
