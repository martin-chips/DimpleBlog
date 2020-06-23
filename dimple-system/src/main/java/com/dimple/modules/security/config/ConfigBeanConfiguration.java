package com.dimple.modules.security.config;

import com.dimple.modules.security.config.bean.LoginProperties;
import com.dimple.modules.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: ConfigBeanConfiguration
 * @description: 配置文件转换Pojo类的 统一配置 类
 * @author: Dimple
 * @date: 06/17/20
 */
@Configuration
public class ConfigBeanConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "login", ignoreUnknownFields = true)
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true)
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
