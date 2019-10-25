package com.dimple.framework.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @className: ApplicationConfig
 * @description: 程序注解配置
 * @author: Dimple
 * @date: 10/22/19
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.dimple.project.**.mapper")
public class ApplicationConfig {

}
